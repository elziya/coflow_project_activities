package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;

public class CoflowNotAllowedMethodException extends CoflowServiceException {

    public CoflowNotAllowedMethodException(String message) {
        super(HttpStatus.METHOD_NOT_ALLOWED, message);
    }

    public CoflowNotAllowedMethodException(Throwable cause, HttpStatus httpStatus) {
        super (cause, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
