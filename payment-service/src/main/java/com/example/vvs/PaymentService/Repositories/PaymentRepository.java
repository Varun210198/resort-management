package com.example.vvs.PaymentService.Repositories;

import com.example.vvs.PaymentService.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentOrderId(String paymentOrderId);
    Optional<Payment> findByReservationId(Long reservationId);
}
