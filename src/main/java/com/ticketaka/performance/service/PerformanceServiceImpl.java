package com.ticketaka.performance.service;

import com.ticketaka.performance.domain.Performance;
import com.ticketaka.performance.domain.PrfSession;
import com.ticketaka.performance.dto.FacilityDTO;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceDetailInfo;
import com.ticketaka.performance.dto.PrfSessionDTO;
import com.ticketaka.performance.dto.response.PerformanceResponse;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;
import com.ticketaka.performance.repository.PerformanceRepository;
import com.ticketaka.performance.repository.PrfSessionRepository;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final PrfSessionRepository prfSessionRepository;
    private final RMapCache<Integer, PrfSession> prfSessionRMapCache;
    private final RedissonClient redissonClient;

    @Override
    @Transactional(readOnly = true)
    public PerformanceResponse getPerformanceById(String prfId) throws Exception {
        Performance performance = performanceRepository.findById(prfId).get();
        PerformanceDetailInfo performanceDetailInfo = performance.toPerformanceDetailInfo();
        List<PrfSessionDTO> prfSessionDTOList = getPrfSessionDTOList(performance.getPrfSessionList());
        FacilityDTO facilityDTO = performance.getFacility().of();
        return new PerformanceResponse(performanceDetailInfo,prfSessionDTOList,facilityDTO);
    }

    private List<PrfSessionDTO> getPrfSessionDTOList(List<PrfSession> prfSessionList) {
        return prfSessionList
                .stream()
                .map(prfSession -> {
                    PrfSessionDTO prfSessionDTO = prfSession.toPrfSessionDTO();
                    prfSessionDTO.setAvailable(
                            (getPrfSessionFromRedis(prfSession.getPrfSessionId()).getRemainingSeat()
                            - getWListSize(prfSession.getPrfSessionId())) > 0);
                    return prfSessionDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PrfSessionSeatResponse getPrfSessionById(int prfSessionId) throws Exception {
        PrfSession prfSession = getPrfSessionFromRedis(prfSessionId);
        int size = getWListSize(prfSessionId);
        int rSeat = prfSession.getRemainingSeat() - size;
        return new PrfSessionSeatResponse(rSeat,prfSession.getTotalSeat());
    }

    private int getWListSize(int prfSessionId) {
        RMapCache<String, Integer> wListRMapCache = redissonClient.getMapCache("wList:" + prfSessionId);
        wListRMapCache.clearExpire();
        return wListRMapCache.values().stream().mapToInt(i -> i).sum();
    }

    private PrfSession getPrfSessionFromRedis(int prfSessionId) {
        return prfSessionRMapCache.get(prfSessionId);
    }
}
