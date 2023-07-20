package com.technokratos.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CourseType {

    PUBLIC("Anyone can join"),
    PRIVATE("To join, you need to know the access code");

    private final String description;
}

