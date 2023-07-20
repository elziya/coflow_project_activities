package com.technokratos.util.mapper;

import com.technokratos.dto.request.ThemeCreationForm;
import com.technokratos.dto.request.ThemeRequest;
import com.technokratos.dto.response.ThemeResponse;
import com.technokratos.models.ThemeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ThemeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    @Mapping(target = "course", ignore = true)
    ThemeEntity formToEntity(ThemeCreationForm theme);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    ThemeEntity requestToEntity(ThemeRequest theme);

    ThemeResponse toResponse(ThemeEntity theme);
}

