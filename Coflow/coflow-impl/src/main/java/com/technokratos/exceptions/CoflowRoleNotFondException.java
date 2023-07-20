package com.technokratos.exceptions;

public class CoflowRoleNotFondException extends CoflowNotFoundException {

    public CoflowRoleNotFondException() {
        super("Role not found");
    }
}

