package com.technokratos.exceptions;

public class CoflowConfirmCodeExpiredException extends CoflowIllegalStateException {

    public CoflowConfirmCodeExpiredException() {
        super("The confirmation code has already expired");
    }
}
