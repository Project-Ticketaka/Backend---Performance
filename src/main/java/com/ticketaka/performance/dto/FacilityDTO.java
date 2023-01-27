package com.ticketaka.performance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class FacilityDTO {
    private String facilityName;
    private String telNo;
    private String relateUrl;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal latitude;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal longitude;

}
