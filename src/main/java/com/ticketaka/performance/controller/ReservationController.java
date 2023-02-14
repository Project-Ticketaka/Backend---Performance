package com.ticketaka.performance.controller;

import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/performance/rsv/")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/check")
    public ResponseEntity<BaseResponse> checkReservation(@RequestBody WaitingListRequest request) {
        try {
            reservationService.insertUserInWaitingList(request);
        } catch (IllegalArgumentException e1) {
            return ResponseEntity.ok(new BaseResponse(StatusCode.NO_VACANCY));
        } catch (Exception e2) {
            System.err.println(e2);
        }

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BaseResponse> withdrawReservation(@RequestBody WaitingListRequest request) {
        try {
            reservationService.removeUserFromWaitingList(request);
        } catch (Exception e) {
            System.err.println(e);
        }
        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createReservation(@RequestBody ReservationRequest request) {
        try {
            reservationService.makeReservation(request);
        } catch (IllegalArgumentException e1) {
            return ResponseEntity.ok(new BaseResponse(StatusCode.NOT_ABLE_TO_CREATE));
        } catch (Exception e2) {
            System.err.println(e2);
        }

        return ResponseEntity.ok(new BaseResponse(StatusCode.OK));
    }
}
