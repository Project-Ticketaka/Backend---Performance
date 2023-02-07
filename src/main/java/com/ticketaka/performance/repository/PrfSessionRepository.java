package com.ticketaka.performance.repository;

import com.ticketaka.performance.domain.PrfSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrfSessionRepository extends JpaRepository<PrfSession, Integer> {
}
