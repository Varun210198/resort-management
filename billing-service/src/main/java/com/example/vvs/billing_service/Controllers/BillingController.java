package com.example.vvs.billing_service.Controllers;

import com.example.vvs.billing_service.DTO.CalculatePriceRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billing")
public class BillingController {
    @PostMapping
    public Double calculateBill(@RequestBody CalculatePriceRequest request){
        return 1001.0;
    }

}
