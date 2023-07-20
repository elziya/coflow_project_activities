package com.technokratos.exceptions;

public class CoflowCourseCreationIllegalArgumentException extends CoflowIllegalArgumentException {

    public CoflowCourseCreationIllegalArgumentException() {
        super("Illegal argument for the course");
    }

    public CoflowCourseCreationIllegalArgumentException(String message) {
        super(message);
    }
}
