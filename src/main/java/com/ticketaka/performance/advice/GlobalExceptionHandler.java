package com.ticketaka.performance.advice;

import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.exception.CustomException.NoCreationAvailableException;
import com.ticketaka.performance.exception.CustomException.NoDataSearchedException;
import com.ticketaka.performance.exception.CustomException.NoVacancyFoundException;
import com.ticketaka.performance.exception.CustomException.ReservationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.JDBCConnectionException;
import org.redisson.client.RedisException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoVacancyFoundException.class)
    public ResponseEntity<BaseResponse> handleNoVacancyFound() {
        log.info(StatusCode.NO_VACANCY.getDescription());
        return ResponseEntity.accepted().body(new BaseResponse(StatusCode.NO_VACANCY));
    }

    @ExceptionHandler(NoCreationAvailableException.class)
    public ResponseEntity<BaseResponse> handleNoCreationAvailable() {
        log.info(StatusCode.NOT_ABLE_TO_CREATE.getDescription());
        return ResponseEntity.badRequest().body(new BaseResponse(StatusCode.NOT_ABLE_TO_CREATE));
    }

    @ExceptionHandler(ReservationFailedException.class)
    public ResponseEntity<BaseResponse> handleReservationFailed() {
        log.info(StatusCode.RESERVATION_FAILED.getDescription());
        return ResponseEntity.internalServerError().body(new BaseResponse(StatusCode.RESERVATION_FAILED));
    }

    @ExceptionHandler(NoDataSearchedException.class)
    public ResponseEntity<BaseResponse> handleNoDataSearched(Exception e) {
        log.info(StatusCode.NO_DATA_SEARCHED.getDescription());
        return ResponseEntity.accepted().body(new BaseResponse(StatusCode.NO_DATA_SEARCHED));
    }

    @ExceptionHandler({NoSuchElementException.class,NullPointerException.class})
    public ResponseEntity<BaseResponse> handleNoSuchElement(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(new BaseResponse(StatusCode.NO_SUCH_ELEMENT));
    }

    @ExceptionHandler(JDBCConnectionException.class)
    public ResponseEntity<BaseResponse> handleJDBCConnection(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new BaseResponse(StatusCode.DB_UNABLE_TO_CONNECT));
    }

    @ExceptionHandler(RedisException.class)
    public ResponseEntity<BaseResponse> handleRedis(Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new BaseResponse(StatusCode.REDIS_TROUBLE_OCCURRED));
    }
}
