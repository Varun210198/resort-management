package com.example.vvs.BookingService.dto.payment;

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
public class CreatePaymentResponse {
    String paymentOrderId;
    Double amount;
    String currency;
    PaymentStatus paymentStatus;
    LocalDateTime expiresAt;
}
