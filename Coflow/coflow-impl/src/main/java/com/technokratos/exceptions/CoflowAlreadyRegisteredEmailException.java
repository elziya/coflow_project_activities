package com.technokratos.exceptions;

public class CoflowAlreadyRegisteredEmailException extends CoflowIllegalStateException {

    public CoflowAlreadyRegisteredEmailException() {
        super("The account with this email has been already registered");
    }
}
