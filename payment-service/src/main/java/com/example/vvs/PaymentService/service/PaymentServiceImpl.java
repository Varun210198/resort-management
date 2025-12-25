package com.example.vvs.PaymentService.service;

import com.example.vvs.PaymentService.client.BookingClient;
import com.example.vvs.PaymentService.dto.CreatePaymentRequest;
import com.example.vvs.PaymentService.dto.CreatePaymentResponse;
import com.example.vvs.PaymentService.dto.UpdatePaymentRequest;
import com.example.vvs.PaymentService.dto.PaymentUpdateToBookingService;
import com.example.vvs.PaymentService.exception.InvalidUpdatePaymentException;
import com.example.vvs.PaymentService.model.Payment;
import com.example.vvs.PaymentService.model.PaymentStatus;
import com.example.vvs.PaymentService.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final BookingClient bookingClient;

    //Manually update payment status (called by you / frontend / Postman)
    @Override
    public CreatePaymentResponse createPayment(CreatePaymentRequest request) {

        String  paymentOrderId = generatePaymentOrderId();

        Payment payment = Payment.builder()
                .paymentOrderId(paymentOrderId)
                .reservationId(request.getReservationId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .paymentStatus(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .transactionRef(null)
                .expiresAt(request.getExpiresAt())
                .build();

        paymentRepository.save(payment);

        return CreatePaymentResponse.builder()
                .paymentOrderId(paymentOrderId)
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .paymentStatus(PaymentStatus.PENDING)
                .expiresAt(request.getExpiresAt())
                .build();
    }

    @Override
    public String processPayment(UpdatePaymentRequest request) {
        Payment payment = paymentRepository.findByPaymentOrderId(request.getPaymentOrderId())
                .orElseThrow(() -> new InvalidUpdatePaymentException("PaymentOrderId "+request.getPaymentOrderId()+" is invalid."));

        if(payment.getExpiresAt().isBefore(LocalDateTime.now()) || request.getPaymentStatus().equals(PaymentStatus.FAILED)){
            payment.setPaymentStatus(PaymentStatus.FAILED);

            PaymentUpdateToBookingService paymentUpdate = PaymentUpdateToBookingService.builder()
                    .reservationId(payment.getReservationId())
                    .paymentStatus(PaymentStatus.FAILED)
                    .build();

            bookingClient.handlePaymentUpdate(paymentUpdate);
            System.out.println("FAILED");
        }
        else if(request.getPaymentStatus().equals(PaymentStatus.SUCCESS)){
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setMode(request.getPaymentMode());
            payment.setTransactionRef(request.getTransactionRef());

            PaymentUpdateToBookingService paymentUpdate = PaymentUpdateToBookingService.builder()
                    .reservationId(payment.getReservationId())
                    .paymentStatus(PaymentStatus.SUCCESS)
                    .build();
            System.out.println(paymentUpdate.getPaymentStatus());
            bookingClient.handlePaymentUpdate(paymentUpdate);
            System.out.println("SUCCESS");
        }

        return "Payment Status Updated successfully";
    }

    @Override
    public List<Payment> getpayments() {
        return paymentRepository.findAll();
    }

    private String generatePaymentOrderId() {
        return "pay_" + UUID.randomUUID().toString().substring(0, 10);
    }
}

