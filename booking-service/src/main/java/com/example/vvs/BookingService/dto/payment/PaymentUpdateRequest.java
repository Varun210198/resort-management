package com.example.vvs.BookingService.dto.payment;

import com.example.vvs.BookingService.model.Payment.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentUpdateRequest {
    private Long reservationId;
    private PaymentStatus paymentStatus;
}
