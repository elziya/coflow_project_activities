package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;

public class CoflowAccessDeniedException extends CoflowServiceException {

    public CoflowAccessDeniedException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}

