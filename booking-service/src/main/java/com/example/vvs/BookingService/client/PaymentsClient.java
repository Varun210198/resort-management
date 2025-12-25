package com.example.vvs.BookingService.client;

import com.example.vvs.BookingService.dto.payment.CreatePaymentRequest;
import com.example.vvs.BookingService.dto.payment.CreatePaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payments-service")
public interface PaymentsClient {
    @PostMapping("/api/payments")
    CreatePaymentResponse createPayment(
            @RequestBody CreatePaymentRequest request
    );
}
