package com.ticketaka.performance.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WaitingListRequest {
    private int prfSessionId;
    private int count;
}
