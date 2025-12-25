package com.example.vvs.PaymentService.DTO;

import com.example.vvs.PaymentService.Models.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentUpdateToBookingService {
    Long reservationId;
    PaymentStatus paymentStatus;
}
