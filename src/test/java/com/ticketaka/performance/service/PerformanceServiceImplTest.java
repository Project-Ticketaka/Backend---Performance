package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.response.PerformanceResponse;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PerformanceServiceImplTest {

    @Autowired PerformanceService performanceService;

    @Test
    void 공연_상세_불러오기() throws Exception {
        // given
        // 공연 가격이 null로 들어옴
        String prfId = "PF202263";

        // when

        PerformanceResponse performanceResponse = performanceService.getPerformanceById(prfId);

        // then
//        performanceResponse.getPerformanceDetailInfo().getTicketPrice().stream().forEach(ticketPriceDTO -> System.out.println(ticketPriceDTO.getPrice() + " " + ticketPriceDTO.getSeatType()));
        assertNull(performanceResponse.getPerformanceDetailInfo().getTicketPrice());
        performanceResponse.getPrfSessionList().stream().forEach(prfSessionDTO -> System.out.println(prfSessionDTO.toString()));
        assertEquals(1,performanceResponse.getPrfSessionList().size());
    }

    @Test
    void 공연_회차_불러오기() throws Exception {
        // given
        int prfSessionId = 3;
        PrfSessionSeatResponse response = performanceService.getPrfSessionById(prfSessionId);

        System.out.println(response.getRemainingSeat());
        System.out.println(response.getTotalSeat());
        assertNotNull(response);
    }
}