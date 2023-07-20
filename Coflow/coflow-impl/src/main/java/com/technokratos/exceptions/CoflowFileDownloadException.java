package com.technokratos.exceptions;

public class CoflowFileDownloadException extends CoflowNotFoundException {

    public CoflowFileDownloadException() {
        super("Can't get file");
    }
}

