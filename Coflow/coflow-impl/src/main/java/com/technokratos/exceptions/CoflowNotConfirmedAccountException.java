package com.technokratos.exceptions;

public class CoflowNotConfirmedAccountException extends CoflowIllegalStateException {

    public CoflowNotConfirmedAccountException() {
        super("The account isn't confirmed");
    }

    public CoflowNotConfirmedAccountException(String message) {
        super(message);
    }
}
