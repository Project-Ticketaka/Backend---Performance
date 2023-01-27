package com.ticketaka.performance.repository;

import com.ticketaka.performance.domain.Performance;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance,Integer> {
    // 제목 중 keyword를 내포하는 공연목록을 조회
    @EntityGraph(attributePaths = {"facility"})
//    @Query(value = "SELECT p FROM Performance p JOIN FETCH p.facility WHERE p.prfTitle LIKE %?1% ORDER BY p.prfLoadedAt DESC")
    Slice<Performance> findByPrfTitleContaining(String keyword, Pageable pageable);

    // 장르별로 조회
    @EntityGraph(attributePaths = {"facility"})
//    @Query(value = "SELECT p FROM Performance p JOIN FETCH p.facility WHERE p.prfGenre = ?1 ORDER BY p.prfLoadedAt DESC")
    Slice<Performance> findByPrfGenre(String genre, Pageable pageable);

}
