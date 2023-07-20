package com.technokratos.exceptions;

import org.springframework.http.HttpStatus;

public class CoflowLessonMaterialsLimitException extends CoflowIllegalStateException {

    public CoflowLessonMaterialsLimitException() {
        super("The limit of lesson materials achieved");
    }
}
