package com.technokratos.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum State {

    CONFIRMED("User's email confirmed"),
    NOT_CONFIRMED("User's email is not confirmed");

    private final String description;

}
