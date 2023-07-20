package com.technokratos.consts;

public interface CoflowCommonConstants {

    /** Время (в часах), в течение которого код подтверждения аккаунта считается валидным */
    Integer ACCOUNT_CONFIRM_CODE_TIME_VALID = 2;

    Integer LESSON_MAX_NUMB_OF_FILES = 5;

    Integer CERTIFICATE_WIDTH = 500;

    Integer CERTIFICATE_HEIGHT = 700;

    String CERTIFICATE_INPUT_FILE_NAME = "source.xhtml";

    String CERTIFICATE_OUTPUT_FILE_NAME = "certificate.jpg";

    Float IMAGE_ATTACHMENT_QUALITY = 0.95F;

    int MIN_STARS = 1;

    int MAX_STARS = 5;
}
