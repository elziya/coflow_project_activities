package com.technokratos.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CoflowServiceException extends RuntimeException {
    private final HttpStatus httpStatus;

    public CoflowServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public CoflowServiceException(Throwable cause, HttpStatus httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }
}

