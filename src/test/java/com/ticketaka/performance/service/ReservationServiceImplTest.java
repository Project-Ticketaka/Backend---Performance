package com.ticketaka.performance.service;

import com.ticketaka.performance.domain.PrfSession;
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
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationServiceImplTest {
    @Autowired ReservationService reservationService;
    @Autowired PerformanceService performanceService;
    @Autowired RedissonClient redissonClient;
    @Autowired RMapCache<Integer, PrfSession> prfSessionRMapCache;

    @Test
    @Order(1)
    void 대기열_테스트() throws Exception {
        // given
        RMapCache<Object, Object> wListMapCache = redissonClient.getMapCache("wList:2");
        WaitingListRequest request1 = new WaitingListRequest("1",2,3);
        WaitingListRequest request2 = new WaitingListRequest("2",2,5);

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
                "1",
                "PF132236",
                "사랑",
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

    @Test
    void 레디스_락_테스트_대기열() throws InterruptedException {
        RMapCache<Object, Object> wListMapCache = redissonClient.getMapCache("wList:3");
        int numberOfThreads = 400;
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        for (int i = 1; i <= numberOfThreads; i++) {
            int n1 = i;
            executorService.submit(() -> {
                WaitingListRequest request = new WaitingListRequest(String.valueOf(n1), 3, 1);
                try {
                    reservationService.insertUserInWaitingList(request);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        wListMapCache.keySet().stream().forEach(str -> System.out.print(str + ", "));
        int size = wListMapCache.values().stream().mapToInt(i -> (int) i).sum();
        assertEquals(size,200);
    }

    @Test
    void 레디스_락_테스트_예약() throws InterruptedException {
        RMapCache<Object, Object> wListMapCache = redissonClient.getMapCache("wList:3");
        int numberOfThreads = 200;
        ExecutorService executorService1 = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch countDownLatch1 = new CountDownLatch(numberOfThreads);
        for(int i = 1; i <= numberOfThreads; i++) {
            int n1 = i;
            executorService1.submit(() -> {
                WaitingListRequest request = new WaitingListRequest(String.valueOf(n1), 3, 1);
                try {
                    reservationService.insertUserInWaitingList(request);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    countDownLatch1.countDown();
                }
            });
        }
        countDownLatch1.await();
        System.out.print("대기열 사용자 목록: ");
        wListMapCache.keySet().stream().forEach(user -> System.out.print(user + ", "));
        CountDownLatch countDownLatch2 = new CountDownLatch(numberOfThreads);
        for (Object key : wListMapCache.keySet()) {
            String user = (String) key;
            executorService1.submit(() -> {
                try {
                    reservationService.makeReservation(new ReservationRequest(user,"PF100000","사랑","poster",3,1));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch2.countDown();
                }
            });
        }
        countDownLatch2.await();
        System.out.println("대기열 사이즈: " + wListMapCache.values().stream().mapToInt(i -> (int)i).sum());
        System.out.println("잔여 좌석: " + prfSessionRMapCache.get(3).getRemainingSeat());
    }
}