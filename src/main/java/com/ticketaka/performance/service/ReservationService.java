package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;

public interface ReservationService {
    void insertUserInWaitingList(WaitingListRequest request) throws Exception;

    void removeUserFromWaitingList(WaitingListRequest request) throws Exception;

    void makeReservation(ReservationRequest request) throws Exception;
}
