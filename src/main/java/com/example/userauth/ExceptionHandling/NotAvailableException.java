package com.example.userauth.ExceptionHandling;

import lombok.Getter;

@Getter
public class NotAvailableException extends RuntimeException {
    private final String errorCode;

    public NotAvailableException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;

    }
}
