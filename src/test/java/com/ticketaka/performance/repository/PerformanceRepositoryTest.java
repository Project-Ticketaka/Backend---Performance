package com.ticketaka.performance.repository;

import com.ticketaka.performance.domain.Performance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PerformanceRepositoryTest {
    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    void 제목으로_조회() {
        // given
        String keyword = "그림";
        Pageable pageable1 = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"prfLoadedAt"));

        // when
        Slice<Performance> performances1 = performanceRepository.findByPrfTitleContaining(keyword,pageable1);

        // then
        assertTrue(performances1.getContent().get(0).getPrfLoadedAt().isAfter(performances1.getContent().get(1).getPrfLoadedAt()));
        assertTrue(performances1.stream().allMatch(performance -> performance.getPrfTitle().contains(keyword)));
    }

    @Test
    void 장르로_조회() {
        // given
        String genre = "대중음악";
        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"prfLoadedAt"));

        // when
        Slice<Performance> performances = performanceRepository.findByPrfGenre(genre, pageable);

        // then
        assertTrue(performances.stream().allMatch(performance -> (performance.getPrfGenre().toString()).equals(genre)));
    }

    @Test
    void 공연_상세_조회() {
        // given
        String prfId = "PF200267";
        String prfIdNull = null;
        String prfIdNoExist = "PF999999";

        // when && then

        // 공연 id가 db에 존재할 때
        Performance performance = performanceRepository.findById(prfId).get();
        assertInstanceOf(Performance.class,performance);

        // 공연 id가 null 값일 때
        assertThrows(InvalidDataAccessApiUsageException.class,() -> {
            performanceRepository.findById(prfIdNull).get();
        });

        // 공연 id가 db에 존재하지 않을 때
        assertThrows(NoSuchElementException.class,() -> {
            performanceRepository.findById(prfIdNoExist).get();
        });
    }
}