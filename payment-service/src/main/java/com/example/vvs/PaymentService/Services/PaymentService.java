package com.example.vvs.PaymentService.Services;

import com.example.vvs.PaymentService.DTO.CreatePaymentRequest;
import com.example.vvs.PaymentService.DTO.CreatePaymentResponse;
import com.example.vvs.PaymentService.DTO.UpdatePaymentRequest;
import com.example.vvs.PaymentService.Models.Payment;

import java.util.List;

public interface PaymentService {
    CreatePaymentResponse createPayment(CreatePaymentRequest request);

    String processPayment(UpdatePaymentRequest request);

    List<Payment> getpayments();
}
