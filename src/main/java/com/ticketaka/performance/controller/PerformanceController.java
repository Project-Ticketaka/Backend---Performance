package com.ticketaka.performance.controller;

import com.ticketaka.performance.dto.PerformanceDTO;
import com.ticketaka.performance.dto.PerformanceDTO.RankedPerformanceInfo;
import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.dto.response.PerformanceResponse;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;
import com.ticketaka.performance.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/performance")
public class PerformanceController {
    private final PerformanceService performanceService;

    @GetMapping("/rank")
    public ResponseEntity<BaseResponse> getPerformanceByRank() {
        List<RankedPerformanceInfo> data = performanceService.getRankedPerformanceInfoList();
        return ResponseEntity.ok(new BaseResponse(StatusCode.OK,data));
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> getPerformanceById(
            @RequestParam(value = "p") String performanceId) throws Exception {
        PerformanceResponse data = performanceService.getPerformanceById(performanceId);
        return ResponseEntity.ok(new BaseResponse(StatusCode.OK,data));
    }
}
