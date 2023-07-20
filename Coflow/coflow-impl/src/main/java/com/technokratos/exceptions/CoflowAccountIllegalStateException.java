package com.technokratos.exceptions;

public class CoflowAccountIllegalStateException extends CoflowIllegalStateException {

    public CoflowAccountIllegalStateException() {
        super("Account has already been confirmed");
    }
}
