package com.example.vvs.PaymentService.service;

import com.example.vvs.PaymentService.dto.CreatePaymentRequest;
import com.example.vvs.PaymentService.dto.CreatePaymentResponse;
import com.example.vvs.PaymentService.dto.UpdatePaymentRequest;
import com.example.vvs.PaymentService.model.Payment;

import java.util.List;

public interface PaymentService {
    CreatePaymentResponse createPayment(CreatePaymentRequest request);

    String processPayment(UpdatePaymentRequest request);

    List<Payment> getpayments();
}
