package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.dto.response.PrfSessionSeatResponse;

public interface ReservationService {
    PrfSessionSeatResponse getPrfSessionById(int prfSessionId) throws Exception;

    void insertUserInWaitingList(WaitingListRequest request) throws Exception;

    void removeUserFromWaitingList(WaitingListRequest request) throws Exception;

    void makeReservation(ReservationRequest request) throws Exception;
}
