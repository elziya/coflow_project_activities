package com.technokratos.exceptions;

public class CoflowLessonNotFoundException extends CoflowNotFoundException {

    public CoflowLessonNotFoundException() {
        super("Lesson not found");
    }
}

