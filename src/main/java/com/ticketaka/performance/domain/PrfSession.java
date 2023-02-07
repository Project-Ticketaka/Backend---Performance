package com.ticketaka.performance.domain;

import com.ticketaka.performance.dto.PrfSessionDTO;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrfSession {
    @Id
    private int prfSessionId;

    @Column(name = "prf_session_date", nullable = false)
    private LocalDate prfSessionDate;

    @Column(name = "prf_session_time", nullable = false)
    private String prfSessionTime;

    @Column(name = "remaining_seat", nullable = false)
    private int remainingSeat;

    @Column(name = "total_seat", nullable = false)
    private int totalSeat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    public PrfSessionDTO toPrfSessionDTO() {
        return PrfSessionDTO.builder()
                .prfSessionId(prfSessionId)
                .prfSessionDate(prfSessionDate)
                .prfSessionTime(prfSessionTime)
                .isAvailable(checkAvailability())
                .build();
    }

    public PrfSessionSeatResponse toPrfSessionSeatResponse() {
        return PrfSessionSeatResponse.builder()
                .remainingSeat(remainingSeat)
                .totalSeat(totalSeat)
                .build();
    }

    private boolean checkAvailability() {
        return remainingSeat > 0;
    }
}
