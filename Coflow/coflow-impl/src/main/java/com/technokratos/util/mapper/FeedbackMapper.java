package com.technokratos.util.mapper;

import com.technokratos.dto.request.FeedbackForm;
import com.technokratos.models.FeedbackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface FeedbackMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    FeedbackEntity toEntity(FeedbackForm feedbackForm);
}
