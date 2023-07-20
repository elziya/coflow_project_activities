package com.technokratos.exceptions;

public class CoflowIllegalConfirmCodeException extends CoflowIllegalArgumentException {

    public CoflowIllegalConfirmCodeException() {
        super("Illegal confirmation code");
    }
}
