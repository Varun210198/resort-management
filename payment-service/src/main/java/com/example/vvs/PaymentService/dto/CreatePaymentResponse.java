package com.example.vvs.PaymentService.DTO;

import com.example.vvs.PaymentService.Models.Payment;
import com.example.vvs.PaymentService.Models.PaymentMode;
import com.example.vvs.PaymentService.Models.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Currency;

@Data
@Builder
public class CreatePaymentResponse {
    String paymentOrderId;
    Double amount;
    String currency;
    PaymentStatus paymentStatus;
    LocalDateTime expiresAt;
}


