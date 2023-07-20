package com.technokratos.exceptions;

public class CoflowFileNotFoundException extends CoflowNotFoundException {

    public CoflowFileNotFoundException() {
        super("File not found");
    }
}
