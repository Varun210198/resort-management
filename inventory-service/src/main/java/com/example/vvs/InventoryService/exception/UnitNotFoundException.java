package com.example.vvs.InventoryService.Exceptions;

public class UnitNotFoundException extends RuntimeException{
    public UnitNotFoundException(String message){
        super(message);
    }
}
