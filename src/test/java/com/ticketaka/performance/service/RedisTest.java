package com.ticketaka.performance.service;

import com.ticketaka.performance.domain.PrfSession;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.repository.PrfSessionRepository;
import org.junit.jupiter.api.Test;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RMapCache<Integer, PrfSession> prfSessionRMapCache;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private PrfSessionRepository prfSessionRepository;
    /**
     * 들어오는 값: memberId, count, sessionId
     *
     * sessionKey = "session:" + sessionId `session:0001`
     * wListKey = "wList:" + sessionId  `wList:0001`
     *
     * 대기열에 넣을 수 있는지 확인
     * vacancy = valueSession.get(sessionKey) - hashWList.size(wListKey);
     *
     * if(vacancy >= count) 가능하다면 {
     *     hashWList.set(wListKey,userId,count)
     * } else {
     *     return NotAvailable
     *
     */

//    @Test
//    void 레디스_입력_테스트() throws InterruptedException {
//        // given
//
//        WaitingListRequest request1 = new WaitingListRequest("user_id_1",1,3);
//        WaitingListRequest request2 = new WaitingListRequest("user_id_2",1,1);
//
//        // when
//        PrfSession prfSession = prfSessionRepository.findById(1).get();
//        prfSessionRMapCache.put(1,prfSession);
//        RMapCache<String, Integer> wListRMapCache = redissonClient.getMapCache("wList:" + request1.getPrfSessionId());
//        wListRMapCache.put(request1.getMemberId(),request1.getCount(),3,TimeUnit.SECONDS);
//
//        // then
//        PrfSession prfSession1 = prfSessionRMapCache.get(1);
//        System.out.println(prfSession1.getPerformance().getPerformanceId());
//
//        int size = wListRMapCache.size();
//        assertEquals(1,size);
//        int count = wListRMapCache.get(request1.getMemberId());
//        assertEquals(3,count);
//
//        Thread.sleep(3300);
//        boolean check = wListRMapCache.containsKey(request1.getMemberId());
//        assertFalse(check);
//        Object o = wListRMapCache.get(request1.getMemberId());
//        assertNull(o);
//        wListRMapCache.clearExpire();
//    }
}
