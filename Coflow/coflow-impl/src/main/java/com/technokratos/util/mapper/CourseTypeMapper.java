package com.technokratos.util.mapper;

import com.technokratos.dto.request.CourseTypeRequest;
import com.technokratos.dto.response.CourseTypeResponse;
import com.technokratos.models.CourseTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CourseTypeMapper {

    @Mapping(target = "name", ignore = true)
    CourseTypeEntity toEntity(CourseTypeRequest courseTypeExtendedRequest);

    CourseTypeResponse toResponse(CourseTypeEntity courseTypeEntity);
}

