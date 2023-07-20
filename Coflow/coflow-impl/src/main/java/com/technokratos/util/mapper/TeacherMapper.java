package com.technokratos.util.mapper;

import com.technokratos.dto.request.TeacherRequest;
import com.technokratos.dto.response.TeacherExtendedResponse;
import com.technokratos.dto.response.TeacherResponse;
import com.technokratos.models.TeacherEntity;
import jdk.jfr.Name;
import org.hibernate.mapping.Set;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TeacherMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "account", ignore = true)
    TeacherEntity toEntity(TeacherRequest language);

    TeacherResponse toResponse(TeacherEntity teacher);

    @Mapping(target = "courses", ignore = true)
    TeacherExtendedResponse toExtendedResponse(TeacherEntity teacher);
}
