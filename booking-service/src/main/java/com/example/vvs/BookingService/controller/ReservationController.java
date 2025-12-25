package com.example.vvs.BookingService.controller;

import com.example.vvs.BookingService.dto.booking.ReservationRequest;
import com.example.vvs.BookingService.dto.booking.ReservationResponse;
import com.example.vvs.BookingService.dto.booking.ReservationUnitResponse;
import com.example.vvs.BookingService.model.Reservation;
import com.example.vvs.BookingService.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService service;

    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody ReservationRequest request){
        ReservationResponse response =  service.createReservation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable("id") Long id ){
        Reservation reservation = service.getReservation(id);
        return ResponseEntity.status(HttpStatus.OK).body(reservation);
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> reservationList = service.getAllReservations();
        return ResponseEntity.status(HttpStatus.OK).body(reservationList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @Valid @RequestBody ReservationRequest request){
        Reservation reservation = service.updateReservation(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") Long id){
        service.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Reservation with id "+id+" deleted successfully");
    }



    //Reservation Units API's
    @GetMapping("units")
    public ResponseEntity<List<ReservationUnitResponse>> getAllReservationUnits(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllReservationUnits());
    }





}
