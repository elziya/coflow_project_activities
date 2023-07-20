package com.technokratos.exceptions;

public class CoflowTemplatesMethodException extends CoflowNotAllowedMethodException {

    public CoflowTemplatesMethodException() {
        super("Can't execute query");
    }
}
