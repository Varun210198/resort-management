package com.example.vvs.BookingService.dto.billing;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculatePriceRequest {

    private Long resortId;
    private Long accoTypeId;
    private List<Long> unitIds;
    private LocalDateTime arrivalDate;
    private LocalDateTime departureDate;
    private Long userId;

}
