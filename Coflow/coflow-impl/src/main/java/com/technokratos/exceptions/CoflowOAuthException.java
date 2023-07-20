package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;

public class CoflowOAuthException extends CoflowServiceException{

    public CoflowOAuthException(String message) {
        super(HttpStatus.METHOD_NOT_ALLOWED, message);
    }

    public CoflowOAuthException(Throwable cause, HttpStatus httpStatus) {
        super(cause, httpStatus);
    }
}