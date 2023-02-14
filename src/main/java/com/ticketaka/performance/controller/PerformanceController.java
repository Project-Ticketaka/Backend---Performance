package com.ticketaka.performance.controller;

import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.dto.response.PerformanceResponse;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;
import com.ticketaka.performance.service.PerformanceService;
import com.ticketaka.performance.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/performance")
public class PerformanceController {
    private final PerformanceService performanceService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> getPerformanceById(
            @RequestParam(value = "p") String performanceId) {
        PerformanceResponse data = performanceService.getPerformanceById(performanceId);
        return ResponseEntity.ok(new BaseResponse(StatusCode.OK,data));
    }

    @GetMapping("/session/{id}")
    public ResponseEntity<BaseResponse> getPrfSessionById(
            @PathVariable(value = "id") int prfSessionId) {
        PrfSessionSeatResponse data = performanceService.getPrfSessionById(prfSessionId);
        return ResponseEntity.ok(new BaseResponse(StatusCode.OK,data));
    }
}
