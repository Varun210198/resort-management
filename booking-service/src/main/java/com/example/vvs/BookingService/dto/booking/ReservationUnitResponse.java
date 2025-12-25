package com.example.vvs.BookingService.dto.booking;

import com.example.vvs.BookingService.model.LockStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationUnitResponse {
    private Long ReservationId;
    private Long UnitId;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private LockStatus lockStatus;
    private LocalDateTime expiresAt;
}
