package com.ticketaka.performance.util.advice;

import com.ticketaka.performance.dto.StatusCode;
import com.ticketaka.performance.dto.response.BaseResponse;
import com.ticketaka.performance.util.exception.CustomException.NoCreationAvailableException;
import com.ticketaka.performance.util.exception.CustomException.NoDataSearchedException;
import com.ticketaka.performance.util.exception.CustomException.NoVacancyFoundException;
import com.ticketaka.performance.util.exception.CustomException.ReservationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.JDBCConnectionException;
import org.redisson.client.RedisException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoVacancyFoundException.class)
    public BaseResponse handleNoVacancyFound() {
        log.info(StatusCode.NO_VACANCY.getDescription());
        return new BaseResponse(StatusCode.NO_VACANCY);
    }

    @ExceptionHandler(NoCreationAvailableException.class)
    public BaseResponse handleNoCreationAvailable() {
        log.info(StatusCode.NOT_ABLE_TO_CREATE.getDescription());
        return new BaseResponse(StatusCode.NOT_ABLE_TO_CREATE);
    }

    @ExceptionHandler(ReservationFailedException.class)
    public BaseResponse handleReservationFailed() {
        log.info(StatusCode.RESERVATION_FAILED.getDescription());
        return new BaseResponse(StatusCode.RESERVATION_FAILED);
    }

    @ExceptionHandler(NoDataSearchedException.class)
    public BaseResponse handleNoDataSearched(Exception e) {
        log.info(StatusCode.NO_DATA_SEARCHED.getDescription());
        return new BaseResponse(StatusCode.NO_DATA_SEARCHED);
    }

    @ExceptionHandler({NoSuchElementException.class,NullPointerException.class})
    public BaseResponse handleNoSuchElement(Exception e) {
        e.printStackTrace();
        return new BaseResponse(StatusCode.NO_SUCH_ELEMENT);
    }

    @ExceptionHandler(JDBCConnectionException.class)
    public BaseResponse handleJDBCConnection(Exception e) {
        e.printStackTrace();
        return new BaseResponse(StatusCode.DB_UNABLE_TO_CONNECT);
    }

    @ExceptionHandler(RedisException.class)
    public BaseResponse handleRedis(Exception e) {
        e.printStackTrace();
        return new BaseResponse(StatusCode.REDIS_TROUBLE_OCCURRED);
    }
}
