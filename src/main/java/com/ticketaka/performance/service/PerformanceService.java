package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.PerformanceDTO;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceDetailInfo;
import com.ticketaka.performance.dto.PerformanceDTO.RankedPerformanceInfo;
import com.ticketaka.performance.dto.response.PerformanceResponse;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;

import java.time.LocalDate;
import java.util.List;

public interface PerformanceService {
    List<RankedPerformanceInfo> getRankedPerformanceInfoList();
    PerformanceResponse getPerformanceById(String prfId) throws Exception;
}
