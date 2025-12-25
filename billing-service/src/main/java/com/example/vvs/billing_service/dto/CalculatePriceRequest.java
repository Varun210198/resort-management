package com.example.vvs.billing_service.DTO;

import lombok.*;

import java.time.LocalDate;
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

    private LocalDate arrivalDate;

    private LocalDate departureDate;

    private Long userId;

    private String couponCode;   // optional
}

