package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.response.PerformanceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class PerformanceServiceImplTest {

    @Autowired PerformanceService performanceService;

    @Test
    void 공연_상세_불러오기() {
        // given
        // 공연 가격이 null로 들어옴
        String prfId = "PF202263";

        // when
        PerformanceResponse performanceResponse = performanceService.getPerformanceById(prfId);

        // then
//        performanceResponse.getPerformanceDetailInfo().getTicketPrice().stream().forEach(ticketPriceDTO -> System.out.println(ticketPriceDTO.getPrice() + " " + ticketPriceDTO.getSeatType()));
        assertNull(performanceResponse.getPerformanceDetailInfo().getTicketPrice());
        assertEquals(1,performanceResponse.getPrfSessionList().size());
    }
}