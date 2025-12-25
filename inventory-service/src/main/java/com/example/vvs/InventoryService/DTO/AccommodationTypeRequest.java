package com.example.vvs.InventoryService.DTO;


import lombok.Data;

@Data
public class AccommodationTypeRequest {
    private String name;
    private String code;
    private Long resortId;
    private String type;
    private Integer capacity;
    private String description;
}

