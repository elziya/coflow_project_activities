package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;

public class CoflowIllegalStateException extends CoflowServiceException {

    public CoflowIllegalStateException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public CoflowIllegalStateException(Throwable cause, HttpStatus httpStatus) {
        super (cause, HttpStatus.BAD_REQUEST);
    }
}
