package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceImplTest {
    @Autowired ReservationService reservationService;
    @Autowired PerformanceService performanceService;
    @Autowired RedissonClient redissonClient;

    @Test
    @Order(1)
    void 대기열_테스트() throws Exception {
        // given
        RMapCache<Object, Object> wListMapCache = redissonClient.getMapCache("wList:2");
        WaitingListRequest request1 = new WaitingListRequest("user1",2,3);
        WaitingListRequest request2 = new WaitingListRequest("user2",2,5);

        // when & then
        reservationService.insertUserInWaitingList(request1);
        PrfSessionSeatResponse resp = performanceService.getPrfSessionById(2);
        assertEquals(197,resp.getRemainingSeat());

        wListMapCache.put(request2.getMemberId(),request2.getCount(),3, TimeUnit.SECONDS);
        Thread.sleep(3500);

        resp = performanceService.getPrfSessionById(2);
        System.out.println(resp.getRemainingSeat());
        assertEquals(197,resp.getRemainingSeat());

    }

    @Test
    @Order(2)
    void 예약_테스트() throws Exception {
        // given
        WaitingListRequest request1 = new WaitingListRequest("user1",2,3);
        ReservationRequest req = new ReservationRequest(
                "user1",
                "user1@email.com",
                "PF132236",
                "http://www.kopis.or.kr/upload/pfmPoster/PF_PF132236_160704_142630.gif",
                2,
                10000);

        // when
        reservationService.insertUserInWaitingList(request1);
        reservationService.makeReservation(req);

        // then
        PrfSessionSeatResponse resp = performanceService.getPrfSessionById(2);
        System.out.println(resp.getRemainingSeat());
    }
}