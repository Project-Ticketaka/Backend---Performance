package com.ticketaka.performance.dto.response;

import com.ticketaka.performance.dto.FacilityDTO;
import com.ticketaka.performance.dto.PerformanceDTO;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceDetailInfo;
import com.ticketaka.performance.dto.PrfSessionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PerformanceResponse {
    private PerformanceDetailInfo performanceDetailInfo;
    private List<PrfSessionDTO> prfSessionList = new ArrayList<>();
    private FacilityDTO facilityDTO;
}
