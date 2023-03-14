package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.PerformanceDTO;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceDetailInfo;
import com.ticketaka.performance.dto.response.PerformanceResponse;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;

public interface PerformanceService {
    PerformanceResponse getPerformanceById(String prfId) throws Exception;
}
