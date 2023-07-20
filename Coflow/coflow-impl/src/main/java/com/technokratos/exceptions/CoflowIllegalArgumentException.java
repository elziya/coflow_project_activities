package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;

public class CoflowIllegalArgumentException extends CoflowServiceException {

    public CoflowIllegalArgumentException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public CoflowIllegalArgumentException(Throwable cause, HttpStatus httpStatus) {
        super (cause, httpStatus);
    }
}
