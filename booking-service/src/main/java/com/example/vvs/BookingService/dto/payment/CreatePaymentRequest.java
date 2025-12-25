package com.example.vvs.BookingService.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {
    Long userId;
    Long reservationId;
    Double amount;
    String currency;
    LocalDateTime expiresAt;
}
