package com.ticketaka.performance.domain;

import com.ticketaka.performance.dto.PerformanceDTO;
import com.ticketaka.performance.dto.PerformanceDTO.RankedPerformanceInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedEntityGraph(
        name = "Rank.performance.facility",
        attributeNodes = {
                @NamedAttributeNode(value = "performance", subgraph = "facility"),
        },
        subgraphs = {@NamedSubgraph(
                name = "facility",
                attributeNodes = {
                        @NamedAttributeNode("facility")
                }
        )}
)
public class PrfRank {
    @Id
    @Column(name = "rank_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rankId;

    @Column(name = "rnum", nullable = false)
    private String rNum;

    @Column(name = "basedate", nullable = false)
    private LocalDate baseDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    public RankedPerformanceInfo toRankedPerformanceInfo() {
        return RankedPerformanceInfo.builder()
                .rNum(rNum)
                .prfId(performance.getPerformanceId())
                .title(performance.getPrfTitle())
                .startDate(performance.getPrfStartDate())
                .endDate(performance.getPrfEndDate())
                .viewingAge(performance.getPrfViewingAge())
                .poster(performance.getPrfPoster())
                .genre(performance.getPrfGenre())
                .facilityName(performance.getFacility().getFacilityName())
                .build();
    }
}
