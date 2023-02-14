package com.ticketaka.performance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.ticketaka.performance.dto.PrfSessionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrfSession implements Serializable {
    @Id
    private int prfSessionId;

    @Column(name = "prf_session_date", nullable = false)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate prfSessionDate;

    @Column(name = "prf_session_time", nullable = false)
    private String prfSessionTime;

    @Column(name = "remaining_seat", nullable = false)
    private int remainingSeat;

    @Column(name = "total_seat", nullable = false)
    private int totalSeat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    @JsonBackReference
    private Performance performance;

    public PrfSessionDTO toPrfSessionDTO() {
        return PrfSessionDTO.builder()
                .prfSessionId(prfSessionId)
                .prfSessionDate(prfSessionDate)
                .prfSessionTime(prfSessionTime)
                .build();
    }

    public void setRemainingSeat(int count) {
        this.remainingSeat -= count;
    }
}
