package com.ticketaka.performance.domain;


import com.ticketaka.performance.domain.constants.PrfOpenrun;
import com.ticketaka.performance.domain.constants.PrfState;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceDetailInfo;
import com.ticketaka.performance.dto.PerformanceDTO.PerformanceInfo;
import com.ticketaka.performance.dto.TicketPriceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    @Id
    @Column(name = "performance_id")
    private String performanceId;

    @Column(name = "prf_title", nullable = false)
    private String prfTitle;

    @Column(name = "prf_start_date", nullable = false)
    private LocalDate prfStartDate;

    @Column(name = "prf_end_date")
    private LocalDate prfEndDate;

    @Column(name = "prf_cast")
    private String prfCast;

    @Column(name = "prf_crew")
    private String prfCrew;

    @Column(name = "prf_runtime", nullable = false)
    private String prfRuntime;

    @Column(name = "prf_prd_comp")
    private String prfPrdComp;

    @Column(name = "prf_viewing_age", nullable = false)
    private String prfViewingAge;

    @Column(name = "prf_ticket_price", nullable = false)
    private String prfTicketPrice;

    @Column(name = "prf_poster", columnDefinition = "TEXT")
    private String prfPoster;

    @Column(name = "prf_story", columnDefinition = "LONGTEXT")
    private String prfStory;

    @Column(name = "prf_genre", nullable = false)
    private String prfGenre;

    @Column(name = "prf_openrun", nullable = false)
    @Enumerated(EnumType.STRING)
    private PrfOpenrun prfOpenrun;

    @Column(name = "prf_styurls", columnDefinition = "TEXT")
    private String prfStyUrls;

    @Column(name = "prf_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private PrfState prfState;

    @Column(name = "prf_loaded_at", nullable = false)
    private LocalDateTime prfLoadedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "performance", orphanRemoval = true)
    private List<PrfSession> prfSessions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    public PerformanceDetailInfo toPerformanceDetailInfo() {
        TicketPriceDTO ticketPriceDTO = new TicketPriceDTO();
        return PerformanceDetailInfo.builder()
                .prfId(performanceId)
                .title(prfTitle)
                .start_date(prfStartDate)
                .end_date(prfEndDate)
                .cast(prfCast)
                .crew(prfCrew)
                .runtime(prfRuntime)
                .proComp(prfPrdComp)
                .viewingAge(prfViewingAge)
                .ticketPrice(ticketPriceDTO.from(prfTicketPrice))
                .poster(prfPoster)
                .story(prfStory)
                .genre(prfGenre.toString())
                .styUrls(generateStyUrlList())
                .state(prfState.toString())
                .build();
    }

    public PerformanceInfo toPerformanceInfo() {
        return PerformanceInfo.builder()
                .prfId(performanceId)
                .title(prfTitle)
                .start_date(prfStartDate)
                .end_date(prfEndDate)
                .viewingAge(prfViewingAge)
                .poster(prfPoster)
                .genre(prfGenre)
                .facilityName(facility.getFacilityName())
                .build();
    }

    private List<String> generateStyUrlList() {
        return new ArrayList<>(Arrays.asList(prfStyUrls.split(",//s")));
    }

}
