package com.ticketaka.performance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PerformanceDTO {

    @Builder
    @Getter
    public static class PerformanceDetailInfo {
        private String prfId;
        private String title;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate startDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate endDate;
        private String cast;
        private String crew;
        private String runtime;
        private String proComp;
        private String viewingAge;
        private List<TicketPriceDTO> ticketPrice = new ArrayList<>();
        private String poster;
        private String story;
        private String genre;
        private List<String> styUrls = new ArrayList<>();
        private String state;
    }

    @Builder
    @Getter
    public static class RankedPerformanceInfo {
        private String rNum;
        private String prfId;
        private String title;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate startDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate endDate;
        private String viewingAge;
        private String genre;
        private String poster;
        private String facilityName;
    }


    @Builder
    @Getter
    public static class PerformanceInfo {
        private String prfId;
        private String title;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate startDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate endDate;
        private String viewingAge;
        private String genre;
        private String poster;
        private String facilityName;

        @Override
        public String toString() {
            return "PerformanceInfo{" +
                    "prfId='" + prfId + '\'' +
                    ", title='" + title + '\'' +
                    ", start_date=" + startDate +
                    ", end_date=" + endDate +
                    ", viewingAge='" + viewingAge + '\'' +
                    ", genre='" + genre + '\'' +
                    ", poster='" + poster + '\'' +
                    ", facilityName='" + facilityName + '\'' +
                    '}';
        }
    }

}
