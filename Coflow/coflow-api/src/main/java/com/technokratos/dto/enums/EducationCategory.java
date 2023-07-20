package com.technokratos.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EducationCategory {

    PROCESS("The course you are taking"),
    DESIRED("The course we would like to join"),
    FINISHED("Finished course");

    private final String description;
}

