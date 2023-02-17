package com.ticketaka.performance.advice;

import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.exception.CustomException;
import com.ticketaka.performance.exception.CustomException.NoCreationAvailableException;
import com.ticketaka.performance.exception.CustomException.NoVacancyFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoVacancyFoundException.class)
    public ResponseEntity<BaseResponse> handleNoVacancyFound() {
        log.info(StatusCode.NO_VACANCY.getDescription());
        return ResponseEntity.badRequest().body(new BaseResponse(StatusCode.NO_VACANCY));
    }

    @ExceptionHandler(NoCreationAvailableException.class)
    public ResponseEntity<BaseResponse> handleNoCreationAvailable() {
        log.info(StatusCode.NOT_ABLE_TO_CREATE.getDescription());
        return ResponseEntity.badRequest().body(new BaseResponse(StatusCode.NOT_ABLE_TO_CREATE));
    }

    @ExceptionHandler(CustomException.ReservationFailedException.class)
    public ResponseEntity<BaseResponse> handleReservationFailed() {
        log.info(StatusCode.RESERVATION_FAILED.getDescription());
        return ResponseEntity.badRequest().body(new BaseResponse(StatusCode.NOT_ABLE_TO_CREATE));
    }


}
