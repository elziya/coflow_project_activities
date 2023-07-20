package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;

public class CoflowTokenRefreshException extends CoflowServiceException {

    public CoflowTokenRefreshException(String token, String message) {
        super(HttpStatus.UNAUTHORIZED, String.format("Failed for [%s]: %s", token, message));
    }
}