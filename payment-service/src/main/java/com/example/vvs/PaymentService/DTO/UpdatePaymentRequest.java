package com.example.vvs.PaymentService.DTO;

import com.example.vvs.PaymentService.Models.PaymentMode;
import com.example.vvs.PaymentService.Models.PaymentStatus;
import lombok.Data;

@Data
public class UpdatePaymentRequest {
    String paymentOrderId;
    String transactionRef;
    PaymentStatus paymentStatus;
    PaymentMode paymentMode;
}
