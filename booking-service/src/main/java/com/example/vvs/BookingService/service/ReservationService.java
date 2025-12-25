package com.example.vvs.BookingService.service;

import com.example.vvs.BookingService.dto.booking.ReservationRequest;
import com.example.vvs.BookingService.dto.booking.ReservationResponse;
import com.example.vvs.BookingService.dto.booking.ReservationUnitResponse;
import com.example.vvs.BookingService.dto.payment.PaymentUpdateRequest;
import com.example.vvs.BookingService.model.Reservation;
import jakarta.validation.Valid;

import java.util.List;

public interface ReservationService {

    ReservationResponse createReservation(@Valid ReservationRequest request);

    Reservation getReservation(Long id);


    List<Reservation> getAllReservations();

    Reservation updateReservation(Long id, ReservationRequest request);

    void deleteReservation(Long id);

    List<ReservationUnitResponse> getAllReservationUnits();

    void processPaymentUpdate(PaymentUpdateRequest request);
}
