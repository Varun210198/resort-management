package com.example.vvs.BookingService.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String s) {
        super(s);
    }
}
