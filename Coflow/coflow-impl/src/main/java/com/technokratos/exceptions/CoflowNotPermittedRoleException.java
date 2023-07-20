package com.technokratos.exceptions;

public class CoflowNotPermittedRoleException extends CoflowAccessDeniedException {

    public CoflowNotPermittedRoleException(String message) {
        super(message);
    }
}
