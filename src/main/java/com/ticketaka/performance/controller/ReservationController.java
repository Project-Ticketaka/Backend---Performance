package com.ticketaka.performance.controller;

import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;
import com.ticketaka.performance.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
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
    public ResponseEntity<BaseResponse> checkReservation(@RequestBody WaitingListRequest request) throws Exception {
        reservationService.insertUserInWaitingList(request);

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }

    @PostMapping("/rsv/withdraw")
    public ResponseEntity<BaseResponse> withdrawReservation(@RequestBody WaitingListRequest request) throws Exception {
        reservationService.removeUserFromWaitingList(request);

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }

    @PostMapping("/rsv/create")
    public ResponseEntity<BaseResponse> createReservation(@RequestBody ReservationRequest request) throws Exception {
        reservationService.makeReservation(request);

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }
}
