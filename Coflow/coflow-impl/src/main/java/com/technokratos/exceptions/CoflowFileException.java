package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;

public class CoflowFileException extends CoflowServiceException {

    public CoflowFileException(String message) {
        super(HttpStatus.METHOD_NOT_ALLOWED, message);
    }

    public CoflowFileException(Throwable cause, HttpStatus httpStatus) {
        super (cause, HttpStatus.METHOD_NOT_ALLOWED);
    }
}

