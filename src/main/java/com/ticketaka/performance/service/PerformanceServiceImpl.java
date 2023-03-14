package com.ticketaka.performance.service;

import com.ticketaka.performance.domain.Performance;
import com.ticketaka.performance.domain.PrfSession;
import com.ticketaka.performance.dto.FacilityDTO;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceDetailInfo;
import com.ticketaka.performance.dto.PrfSessionDTO;
import com.ticketaka.performance.dto.response.PerformanceResponse;
import com.ticketaka.performance.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;

    @Override
    @Transactional(readOnly = true)
    public PerformanceResponse getPerformanceById(String prfId) throws Exception {
        Performance performance = performanceRepository.findById(prfId).get();
        PerformanceDetailInfo performanceDetailInfo = performance.toPerformanceDetailInfo();
        List<PrfSessionDTO> prfSessionDTOList = performance.getPrfSessionList().stream().map(PrfSession::toPrfSessionDTO).collect(Collectors.toList());
        FacilityDTO facilityDTO = performance.getFacility().of();
        return new PerformanceResponse(performanceDetailInfo,prfSessionDTOList,facilityDTO);
    }
}
