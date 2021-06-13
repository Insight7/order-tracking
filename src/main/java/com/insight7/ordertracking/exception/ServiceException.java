package com.insight7.ordertracking.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {
    private final HttpStatus statusCode;

    public ServiceException(String message, Throwable cause, HttpStatus statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public ServiceException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
