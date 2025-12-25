package com.example.vvs.PaymentService.Controllers;

import com.example.vvs.PaymentService.DTO.CreatePaymentRequest;
import com.example.vvs.PaymentService.DTO.CreatePaymentResponse;
import com.example.vvs.PaymentService.DTO.UpdatePaymentRequest;
import com.example.vvs.PaymentService.Models.Payment;
import com.example.vvs.PaymentService.Services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public CreatePaymentResponse createPayment(@RequestBody CreatePaymentRequest request){
        return paymentService.createPayment(request);
    }

    @PatchMapping
    public String updatePayment(@RequestBody UpdatePaymentRequest request){
        return paymentService.processPayment(request);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getPayments(){
        List<Payment> payments =  paymentService.getpayments();
        return ResponseEntity.status(HttpStatus.OK).body(payments);
    }
}


