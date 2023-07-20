package com.technokratos.exceptions;

public class CoflowEducationCategoryNotFoundException extends CoflowNotFoundException {

    public CoflowEducationCategoryNotFoundException() {
        super("Education category not found");
    }
}
