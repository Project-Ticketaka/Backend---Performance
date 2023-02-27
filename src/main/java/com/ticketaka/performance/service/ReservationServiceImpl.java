package com.ticketaka.performance.service;

import com.ticketaka.performance.domain.PrfSession;
import com.ticketaka.performance.dto.ReservationDTO;
import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.exception.CustomException.NoCreationAvailableException;
import com.ticketaka.performance.exception.CustomException.NoVacancyFoundException;
import com.ticketaka.performance.exception.CustomException.ReservationFailedException;
import com.ticketaka.performance.feign.ReservationFeignClient;
import com.ticketaka.performance.lock.RedissonLock;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
    private final RMapCache<Integer, PrfSession> prfSessionRMapCache;
    private final RedissonClient redissonClient;
    private final ReservationFeignClient reservationFeignClient;

    /**
     *  vacancy = 잔여 좌석 - 대기열에 있는 모든 인원 수
     *  vacancy 가 존재하면 대기열에 userId와 인원수를 넣고 만료 시간을 3분으로 설정
     *  3분이 지나면 자동으로 대기열에서 제거
     *  vacancy 가 존재하지 않으면 예외를 던짐
     */
    @Override
    @RedissonLock(key="PrfSessionId")
    public void insertUserInWaitingList(WaitingListRequest request) throws Exception {
        RMapCache<String, Integer> wListRMapCache = redissonClient.getMapCache("wList:" + request.getPrfSessionId());
        wListRMapCache.clearExpire();
        int remainingSeat = prfSessionRMapCache.get(request.getPrfSessionId()).getRemainingSeat();
        int sum = wListRMapCache.values().stream().mapToInt(i -> i).sum();
        int vacancy = remainingSeat - sum;

        int count = request.getCount();
        if(vacancy >= count) {
            wListRMapCache.put(request.getMemberId(), count, 3, TimeUnit.MINUTES);
        } else {
            throw new NoVacancyFoundException();
        }
    }

    @Override
    public void removeUserFromWaitingList(WaitingListRequest request) throws Exception {
        RMapCache<Object, Object> wListRMapCache = redissonClient.getMapCache("wList:" + request.getPrfSessionId());
        wListRMapCache.remove(request.getMemberId());
    }

    /**
     * 요청이 들어오면 해당 memberId에 상응하는 값을 가지고 와서 count에 저장 후 대기열에서 해당 memberId의 필드를 제거
     * count가 null값이면 NOT_ABLE_TO_CREATE 예외를 던짐
     * reservation 서버로 요청을 보내고 ok 응답이 오지 않으면 RESERVATION_FAILED 예외를 던짐
     * session 의 잔여좌석을 count 만큼 감소시킴
     */
    @Override
    @RedissonLock(key="PrfSessionId", waitTime = 15L, leaseTime = 5L)
    public void makeReservation(ReservationRequest request) throws Exception {
        RMapCache<String, Integer> wListRMapCache = redissonClient.getMapCache("wList:" + request.getPrfSessionId());
        Integer count = wListRMapCache.remove(request.getMemberId());
        if(count == null) {
            throw new NoCreationAvailableException();
        }

        PrfSession prfSession = prfSessionRMapCache.get(request.getPrfSessionId());
        ReservationDTO reservationDTO = new ReservationDTO().from(request, count, prfSession);
        BaseResponse response = reservationFeignClient.createReservation(reservationDTO);

        if(response.getCode() != StatusCode.OK.getCode()) {
            throw new ReservationFailedException();
        }
        prfSession.setRemainingSeat(count);
        prfSessionRMapCache.put(request.getPrfSessionId(),prfSession);
    }
}
