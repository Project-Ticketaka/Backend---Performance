package com.ticketaka.performance.repository;

import com.ticketaka.performance.domain.Performance;
import com.ticketaka.performance.domain.PrfSession;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public interface PrfSessionRepository extends JpaRepository<PrfSession, Integer> {

    @Query("SELECT p.prfSessionId FROM PrfSession p")
    List<Integer> findAllIds();
}
