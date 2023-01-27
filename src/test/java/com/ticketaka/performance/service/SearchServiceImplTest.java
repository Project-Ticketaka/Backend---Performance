package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.PerformanceDTO;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchServiceImplTest {
    @Autowired
    private SearchService searchService;

    @Test
    void 제목으로_검색() {
        // given
        String keyword = "그림";
        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"prfLoadedAt"));

        // when
        Slice<PerformanceInfo> performanceInfoSlice = searchService.getPerformanceSliceByKeyword(keyword, pageable);

        // then
        assertInstanceOf(PerformanceInfo.class,performanceInfoSlice.getContent().get(0));

    }

    @Test
    void 장르로_조회() {
        // given
        String genre = "대중음악";
        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"prfLoadedAt"));

        // when
        Slice<PerformanceInfo> performanceInfoSlice = searchService.getPerformanceSliceByGenre(genre, pageable);

        // then
        assertInstanceOf(PerformanceInfo.class,performanceInfoSlice.getContent().get(0));

    }
}