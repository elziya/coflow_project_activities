package com.technokratos.util.mapper;

import com.technokratos.dto.request.LanguageRequest;
import com.technokratos.models.LanguageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface LanguageMapper {

    @Mapping(target = "name", ignore = true)
    LanguageEntity toEntity(LanguageRequest language);
}

