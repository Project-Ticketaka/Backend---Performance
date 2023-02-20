package com.ticketaka.performance.controller;

import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.exception.CustomException;
import com.ticketaka.performance.exception.CustomException.NoVacancyFoundException;
import com.ticketaka.performance.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/performance/rsv/")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/check")
    public ResponseEntity<BaseResponse> checkReservation(@RequestBody WaitingListRequest request) throws Exception {
        reservationService.insertUserInWaitingList(request);

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BaseResponse> withdrawReservation(@RequestBody WaitingListRequest request) throws Exception {
        reservationService.removeUserFromWaitingList(request);

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createReservation(@RequestBody ReservationRequest request) throws Exception {
        reservationService.makeReservation(request);

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }
}
