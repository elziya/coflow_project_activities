package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;

public class CoflowNotFoundException extends CoflowServiceException {

    public CoflowNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public CoflowNotFoundException(Throwable cause, HttpStatus httpStatus) {
        super (cause, HttpStatus.NOT_FOUND);
    }
}

