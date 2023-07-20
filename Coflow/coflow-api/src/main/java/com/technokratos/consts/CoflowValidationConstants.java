package com.technokratos.consts;

public interface CoflowValidationConstants {

    String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{1,60}$";

    Integer PASSWORD_MIN_LENGTH = 8;

    Integer PASSWORD_MAX_LENGTH = 60;

    Integer LESSON_FILES_MAX_COUNT = 5;
}

