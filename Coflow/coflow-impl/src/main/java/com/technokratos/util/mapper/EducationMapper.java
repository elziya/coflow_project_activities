package com.technokratos.util.mapper;

import com.technokratos.dto.response.EducationResponse;
import com.technokratos.models.EducationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        uses = {CourseTypeMapper.class, LanguageMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface EducationMapper {

    EducationResponse toResponse(EducationEntity educationEntity);
}

