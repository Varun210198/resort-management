package com.example.vvs.PaymentService.Exceptions;

public class InvalidUpdatePaymentException extends RuntimeException{
    public InvalidUpdatePaymentException(String s){
        super(s);
    }
}
