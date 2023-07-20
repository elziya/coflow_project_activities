package com.technokratos.exceptions;

public class CoflowCourseTypeNotFoundException extends CoflowNotFoundException {

    public CoflowCourseTypeNotFoundException() {
        super("Course type not found");
    }
}
