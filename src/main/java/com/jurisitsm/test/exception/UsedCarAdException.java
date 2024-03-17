package com.jurisitsm.test.exception;

import org.springframework.http.HttpStatus;

public class UsedCarAdException extends Exception{

    private final HttpStatus httpStatus;

    public UsedCarAdException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
