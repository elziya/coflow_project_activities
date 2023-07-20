package com.technokratos.exceptions;

public class CoflowCourseFeedbackAlreadyExistingException extends CoflowIllegalStateException {

    public CoflowCourseFeedbackAlreadyExistingException() {
        super("Feedback for this course from this account already exists");
    }
}
