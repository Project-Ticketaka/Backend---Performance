package com.ticketaka.performance.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PrfSessionSeatResponse {
    private int remainingSeat;
    private int totalSeat;
}
