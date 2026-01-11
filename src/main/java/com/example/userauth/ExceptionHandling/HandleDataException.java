package com.example.userauth.ExceptionHandling;

import lombok.Getter;

@Getter
public class HandleDataException extends RuntimeException{
    private final String errorCode;

    public HandleDataException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
