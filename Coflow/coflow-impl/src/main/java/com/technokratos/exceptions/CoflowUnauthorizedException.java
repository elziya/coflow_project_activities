package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;

public class CoflowUnauthorizedException extends CoflowServiceException {

    public CoflowUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}