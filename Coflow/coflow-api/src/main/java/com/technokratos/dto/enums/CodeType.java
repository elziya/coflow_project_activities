package com.technokratos.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CodeType {

    ACCOUNT_CONFIRM("Account email confirmation"),
    PASSWORD_CONFIRM("Account's password confirmation");

    private final String description;

}

