package com.example.vvs.PaymentService.Clients;

import com.example.vvs.PaymentService.DTO.PaymentUpdateToBookingService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "booking-service")
public interface BookingClient {
    @PostMapping("/internal/payments/update")
    void handlePaymentUpdate(@RequestBody PaymentUpdateToBookingService response);
}
