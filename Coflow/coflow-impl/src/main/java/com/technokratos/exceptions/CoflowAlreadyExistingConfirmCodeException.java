package com.technokratos.exceptions;

public class CoflowAlreadyExistingConfirmCodeException extends CoflowIllegalStateException {

    public CoflowAlreadyExistingConfirmCodeException() {
        super("There is already confirmation code");
    }
}
