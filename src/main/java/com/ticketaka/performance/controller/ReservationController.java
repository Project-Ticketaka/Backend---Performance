package com.ticketaka.performance.controller;

import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;
import com.ticketaka.performance.service.ReservationService;
import jodd.util.collection.MapEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/performance")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/session/{id}")
    public ResponseEntity<BaseResponse> getPrfSessionById(
            @PathVariable(value = "id") int prfSessionId) throws Exception {
        PrfSessionSeatResponse data = reservationService.getPrfSessionById(prfSessionId);
        return ResponseEntity.ok(new BaseResponse(StatusCode.OK,data));
    }

    @PostMapping("/rsv/check")
    public ResponseEntity<BaseResponse> checkReservation(
            @RequestHeader Map<String, String> header,
            @RequestBody WaitingListRequest request) throws Exception {

        reservationService.insertUserInWaitingList(header, request);

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }

    @PostMapping("/rsv/withdraw")
    public ResponseEntity<BaseResponse> withdrawReservation(
            @RequestHeader Map<String, String> header,
            @RequestBody WaitingListRequest request) throws Exception {
        reservationService.removeUserFromWaitingList(header, request);

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }

    @PostMapping("/rsv/create")
    public ResponseEntity<BaseResponse> createReservation(
            @RequestHeader Map<String, String> header,
            @RequestBody ReservationRequest request) throws Exception {
        reservationService.makeReservation(header, request);

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }
}
