package com.jurisitsm.test.exception;

import org.springframework.http.HttpStatus;

public class UsedCarAdException extends Exception{

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public UsedCarAdException(String message){
        super(message);
    }

    public UsedCarAdException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
