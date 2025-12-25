package com.example.vvs.InventoryService.Exceptions;


import lombok.Data;

@Data
public class AccommodationTypeResponse {
    private Long id;
    private String name;
    private String code;
    private Integer capacity;
    private String type;
    private String description;
    private Long resortId;
}

