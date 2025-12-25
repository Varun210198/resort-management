package com.example.vvs.BookingService.dto.booking;

import com.example.vvs.BookingService.model.Payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Long reservationId;
    private Long userId;
    private Double totalAmount;
    private String currency;
    private LocalDateTime expiresAt;
    private String paymentOrderId;
    private PaymentStatus paymentStatus;
}


