package com.example.vvs.BookingService.client;

import com.example.vvs.BookingService.dto.billing.CalculatePriceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "billing-service")
public interface BillingClient {
    @PostMapping("/api/billing")
    Double calculatePrice(@RequestBody CalculatePriceRequest request);
}
