package com.example.vvs.BookingService.controller;

import com.example.vvs.BookingService.dto.payment.PaymentUpdateRequest;
import com.example.vvs.BookingService.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/payments")
@RequiredArgsConstructor
public class PaymentUpdateController {

    private final ReservationService reservationService;

    @PostMapping("/update")
    public ResponseEntity<Void> handlePaymentUpdate(
            @RequestBody PaymentUpdateRequest request) {

        reservationService.processPaymentUpdate(request);
        return ResponseEntity.ok().build();
    }
}
