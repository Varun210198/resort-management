package com.example.vvs.InventoryService.Exceptions;

import jakarta.validation.constraints.NotBlank;

public class UnitAlreadyExistsException extends Throwable {
    public UnitAlreadyExistsException(@NotBlank(message = "Unit number cannot be blank") String s) {
        super(s);
    }
}
