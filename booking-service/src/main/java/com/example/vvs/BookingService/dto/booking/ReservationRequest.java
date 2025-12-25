package com.example.vvs.BookingService.dto.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Resort ID is required")
    private Long resortId;

    @NotNull(message = "Accommodation Type ID is required")
    private Long accoTypeId;

    @NotNull(message = "Arrival date is required")
    private LocalDateTime arrivalDate;

    @NotNull(message = "Departure date is required")
    private LocalDateTime departureDate;

    // List of units selected for this reservation (IDs from Inventory Service)
    private List<Long> unitIds;

    // Optional: status can be set manually (else default = PENDING)
    private String status;
}
