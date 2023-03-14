package com.ticketaka.performance.feign;


import com.ticketaka.performance.dto.ReservationDTO;
import com.ticketaka.performance.dto.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="MemberFeignClient", url="${member.url}:"+"${member.port}", path = "/reservation")
public interface ReservationFeignClient {
    @PostMapping("/create")
    BaseResponse createReservation(@RequestBody ReservationDTO reservationDTO);
}
