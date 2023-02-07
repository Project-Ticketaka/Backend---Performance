package com.ticketaka.performance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class PrfSessionDTO {
    private int prfSessionId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate prfSessionDate;

    private String prfSessionTime;

    private boolean isAvailable;

    @Override
    public String toString() {
        return "PrfSessionDTO{" +
                "prfSessionId=" + prfSessionId +
                ", prfSessionDate=" + prfSessionDate +
                ", prfSessionTime='" + prfSessionTime + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
