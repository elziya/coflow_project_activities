package com.technokratos.exceptions;

public class CoflowTeacherAlreadyExistsException extends CoflowIllegalStateException {

    public CoflowTeacherAlreadyExistsException() {
            super("Teacher already exists");
        }
}
