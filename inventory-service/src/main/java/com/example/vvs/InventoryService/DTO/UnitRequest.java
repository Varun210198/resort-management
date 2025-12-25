package com.example.vvs.InventoryService.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UnitRequest {

    @NotNull(message = "Accommodation Type ID is required")
    private Long accommodationTypeId;

    @Column(unique = true)
    @NotBlank(message = "Unit number cannot be blank")
    private String unitNumber;

    @NotNull(message = "Active flag is required")
    private Boolean isActive;
}
