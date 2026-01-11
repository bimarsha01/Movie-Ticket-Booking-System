package com.example.userauth.ExceptionHandling;

import lombok.Getter;

@Getter
public class HandleTimeExceedException extends RuntimeException {
    private final String errorCode;

    public HandleTimeExceedException(String errorCode,String message){
        super(message);
        this.errorCode = errorCode;
    }
}
