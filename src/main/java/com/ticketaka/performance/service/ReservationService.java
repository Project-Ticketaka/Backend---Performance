package com.ticketaka.performance.service;

import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;

public interface ReservationService {
    void insertUserInWaitingList(WaitingListRequest request);

    void removeUserFromWaitingList(WaitingListRequest request);

    void makeReservation(ReservationRequest request);
}
