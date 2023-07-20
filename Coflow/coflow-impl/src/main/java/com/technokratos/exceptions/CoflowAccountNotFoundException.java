package com.technokratos.exceptions;

public class CoflowAccountNotFoundException extends CoflowNotFoundException {

    public CoflowAccountNotFoundException() {
        super("Account not found");
    }
}
