package com.example.vvs.InventoryService.DTO;


import lombok.Data;

@Data
public class UnitResponse {
    private Long id;
    private String unitNumber;
    private Boolean isActive;
    private Long accommodationTypeId;
    private String accommodationTypeName;
}

