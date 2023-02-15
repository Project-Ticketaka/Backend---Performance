package com.ticketaka.performance.feign;


import com.ticketaka.performance.dto.ReservationDTO;
import com.ticketaka.performance.dto.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="FeignClient", url="${reservation.url}:"+"${reservation.port}", path = "/reservation")
public interface ReservationFeignClient {
    @PostMapping("/create")
    ResponseEntity<String> createReservation(@RequestBody ReservationDTO reservationDTO) throws Exception;
}
