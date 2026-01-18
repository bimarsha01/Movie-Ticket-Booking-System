package com.example.userauth.ExceptionHandling;

import lombok.Getter;

@Getter
public class BadCredentialsException extends RuntimeException{
    private final String errorCode;

    public BadCredentialsException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
