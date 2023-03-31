package com.ticketaka.performance.service;

import com.ticketaka.performance.domain.Performance;
import com.ticketaka.performance.domain.PrfRank;
import com.ticketaka.performance.domain.PrfSession;
import com.ticketaka.performance.dto.FacilityDTO;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceDetailInfo;
import com.ticketaka.performance.dto.PerformanceDTO.RankedPerformanceInfo;
import com.ticketaka.performance.dto.PrfSessionDTO;
import com.ticketaka.performance.dto.response.PerformanceResponse;
import com.ticketaka.performance.repository.PerformanceRepository;
import com.ticketaka.performance.repository.PrfRankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final PrfRankRepository prfRankRepository;

    @Override
    public List<RankedPerformanceInfo> getRankedPerformanceInfoList() {
        LocalDate baseDate = LocalDate.now(ZoneId.of("Asia/Seoul")).minusDays(1);
        log.info("BaseDate: " + baseDate.toString());
        List<RankedPerformanceInfo> rankedPerformanceInfoList = getRankedPerformanceByBaseTime(baseDate);
        if (rankedPerformanceInfoList.isEmpty()) {
            baseDate = baseDate.minusDays(1);
            log.info("BaseDate: " + baseDate.toString());
            rankedPerformanceInfoList = getRankedPerformanceByBaseTime(baseDate);
        }

        return rankedPerformanceInfoList;
    }

    @Transactional(readOnly = true)
    List<RankedPerformanceInfo> getRankedPerformanceByBaseTime(LocalDate baseDate) {
        return prfRankRepository
                .findTop10ByBaseDate(baseDate)
                .stream().map(PrfRank::toRankedPerformanceInfo)
                .collect(Collectors.toList());
    }

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
