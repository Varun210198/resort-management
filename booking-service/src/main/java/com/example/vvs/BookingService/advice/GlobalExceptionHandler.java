package com.example.vvs.BookingService.advice;

import com.example.vvs.BookingService.exception.ReservationNotFoundException;
import com.example.vvs.BookingService.exception.UnitsNotAvailableForBookingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnitsNotAvailableForBookingException.class)
    public ResponseEntity<String> handleUnitsNotAvailable(UnitsNotAvailableForBookingException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handleReservationNotFound(ReservationNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
