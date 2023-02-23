package com.ticketaka.performance.service;

import com.ticketaka.performance.domain.Performance;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceInfo;
import com.ticketaka.performance.util.exception.CustomException.NoDataSearchedException;
import com.ticketaka.performance.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final PerformanceRepository performanceRepository;

    @Override
    @Transactional(readOnly = true)
    public Slice<PerformanceInfo> getPerformanceSliceByKeyword(String keyword, Pageable pageable) throws Exception {
        Slice<Performance> performances = performanceRepository.findByPrfTitleContaining(keyword, pageable);
        if(performances.isEmpty()) {
            throw new NoDataSearchedException();
        }
        return performances.map(Performance::toPerformanceInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<PerformanceInfo> getPerformanceSliceByGenre(String genre, Pageable pageable) throws Exception {
        Slice<Performance> performances = performanceRepository.findByPrfGenre(genre, pageable);
        if(performances.isEmpty()) {
            throw new NoDataSearchedException();
        }
        return performances.map(Performance::toPerformanceInfo);
    }
}
