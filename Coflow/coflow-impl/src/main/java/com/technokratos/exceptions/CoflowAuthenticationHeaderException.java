package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CoflowAuthenticationHeaderException extends AuthenticationException {

    public CoflowAuthenticationHeaderException(String msg) {
        super(msg);
    }

    public CoflowAuthenticationHeaderException(String msg, Throwable t) {
        super(msg, t);
    }
}