package com.example.userauth.ExceptionHandling;

import lombok.Getter;

@Getter
public class AlreadyExistException extends RuntimeException{

        private final String errorCode;

        public AlreadyExistException(String errorCode, String message) {
            super(message);
            this.errorCode = errorCode;
        }
    }


