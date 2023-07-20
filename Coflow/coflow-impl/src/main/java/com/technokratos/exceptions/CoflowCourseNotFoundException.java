package com.technokratos.exceptions;

public class CoflowCourseNotFoundException extends CoflowNotFoundException {

    public CoflowCourseNotFoundException() {
        super("Course not found");
    }
}
