package com.ticketaka.performance.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CheckAvailabilityRequest {
    private int prfSessionId;
    private int count;
}
