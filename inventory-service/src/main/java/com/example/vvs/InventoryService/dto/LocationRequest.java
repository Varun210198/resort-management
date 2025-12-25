package com.example.vvs.InventoryService.DTO;

import lombok.*;

import lombok.Data;
@Getter
@Setter
@Data
public class LocationRequest {
    @NonNull
    private String name;
}

