package com.example.vvs.InventoryService.DTO;

import com.example.vvs.InventoryService.Models.Location;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResortRequest {

    @NotBlank(message = "Resort name cannot be empty")
    private String name;

    @NotNull(message = "Location ID is required")
    private Long locationId;
}

