package com.ticketaka.performance.domain;

import com.ticketaka.performance.dto.FacilityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Facility {
    @Id
    @Column(name = "facility_id")
    private String facilityId;

    @Column(name = "facility_name")
    private String facilityName;

    @Column(name = "facility_telno")
    private String facilityTelNo;

    @Column(name = "facility_relateurl", columnDefinition = "TEXT")
    private String facilityRelateUrl;

    @Column(name = "facility_address", columnDefinition = "TEXT")
    private String facilityAddress;

    @Column(name = "facility_latitude", precision = 10, scale = 7)
    private BigDecimal facilityLatitude;

    @Column(name = "facility_longitude", precision = 11, scale = 7)
    private BigDecimal facilityLongitude;

    @OneToMany(mappedBy = "facility", fetch = FetchType.LAZY)
    private List<Performance> performances = new ArrayList<>();

    public FacilityDTO of() {
        return FacilityDTO.builder()
                .facilityName(facilityName)
                .telNo(facilityTelNo)
                .relateUrl(facilityRelateUrl)
                .address(facilityAddress)
                .latitude(facilityLatitude)
                .longitude(facilityLongitude)
                .build();
    }
}
