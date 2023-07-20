package com.technokratos.util.mapper;

import com.technokratos.dto.request.CourseExtendedCreationForm;
import com.technokratos.dto.response.CourseExtendedResponse;
import com.technokratos.dto.response.CourseResponse;
import com.technokratos.dto.response.SearchCourseResponse;
import com.technokratos.models.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        uses = {CourseTypeMapper.class, LanguageMapper.class, EducationMapper.class, ThemeMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "accessCode", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "certificationTemplate", ignore = true)
    @Mapping(target = "feedbacks", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "themes", ignore = true)
    @Mapping(target = "isFinished", ignore = true)
    CourseEntity toEntity(CourseExtendedCreationForm course);

    CourseResponse toResponse(CourseEntity course);

    @Mapping(target = "teachers", ignore = true)
    @Mapping(target = "students", ignore = true)
    CourseExtendedResponse toExtendedResponse(CourseEntity course);

    @Mapping(target = "teachers", ignore = true)
    SearchCourseResponse toSearchResponse(CourseEntity course);
}

