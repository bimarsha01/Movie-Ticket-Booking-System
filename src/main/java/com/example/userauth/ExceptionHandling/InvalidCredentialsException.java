package com.example.userauth.ExceptionHandling;

import lombok.Getter;

@Getter
public class InvalidCredentialsException extends RuntimeException {
    private final String errorCode;

    public InvalidCredentialsException(String errorCode , String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
