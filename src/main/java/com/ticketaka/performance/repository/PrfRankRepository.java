package com.ticketaka.performance.repository;

import com.ticketaka.performance.domain.PrfRank;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrfRankRepository extends JpaRepository<PrfRank, Integer> {
    @EntityGraph(value = "Rank.performance.facility")
    List<PrfRank> findTop10ByBaseDate(LocalDate baseTime);
}
