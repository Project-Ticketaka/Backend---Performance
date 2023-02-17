package com.ticketaka.performance.exception;

public class CustomException {
    public static class NoVacancyFoundException extends RuntimeException {
    }

    public static class NoCreationAvailableException extends RuntimeException {
    }

    public static class ReservationFailedException extends RuntimeException {
    }

}
