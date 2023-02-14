package com.ticketaka.performance.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReservationRequest {
    private String memberId;
    private int prfSessionId;
    private int price;
}
