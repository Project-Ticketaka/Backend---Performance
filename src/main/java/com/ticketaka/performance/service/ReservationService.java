package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;

import java.util.Map;

public interface ReservationService {
    PrfSessionSeatResponse getPrfSessionById(int prfSessionId) throws Exception;

    void insertUserInWaitingList(Map<String,String> header, WaitingListRequest request) throws Exception;

    void removeUserFromWaitingList(Map<String,String> header, WaitingListRequest request) throws Exception;

    void makeReservation(Map<String,String> header, ReservationRequest request) throws Exception;
}
