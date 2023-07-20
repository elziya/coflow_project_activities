package com.technokratos.util.mapper;

import com.technokratos.dto.request.LessonCreationForm;
import com.technokratos.dto.response.LessonResponse;
import com.technokratos.models.LessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {FileInfoMapper.class, ThemeMapper.class})
public interface LessonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "theme", ignore = true)
    @Mapping(target = "materials", ignore = true)
    @Mapping(target = "comments", ignore = true)
    LessonEntity toEntity(LessonCreationForm language);

    LessonResponse toResponse(LessonEntity lessonEntity);
}

