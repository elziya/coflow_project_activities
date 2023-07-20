package com.technokratos.exceptions;

public class CoflowTeacherNotFoundException extends CoflowNotFoundException{

    public CoflowTeacherNotFoundException() {
        super("Teacher not found");
    }
}
