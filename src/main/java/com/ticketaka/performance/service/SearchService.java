package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.PerformanceDTO.PerformanceInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface SearchService {
    Slice<PerformanceInfo> getPerformanceSliceByKeyword(String keyword, Pageable pageable);

    Slice<PerformanceInfo> getPerformanceSliceByGenre(String genre, Pageable pageable);
}
