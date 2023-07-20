package com.technokratos.exceptions;

public class CoflowFileUploadException extends CoflowFileException {

    public CoflowFileUploadException() {
        super("Can't upload file");
    }
}
