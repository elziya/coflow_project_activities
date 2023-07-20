package com.technokratos.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("Пользователь"),
    ADMIN("Администратор"),
    STUDENT("Учащийся"),
    TEACHER("Учитель");

    private final String description;

}
