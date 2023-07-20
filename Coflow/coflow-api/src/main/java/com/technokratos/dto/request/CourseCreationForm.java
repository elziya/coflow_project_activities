package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Course creation form")
public class CourseCreationForm {

    @ApiModelProperty(value = "Course name", example = "Learning Java")
    private String name;

    @ApiModelProperty(value = "Language")
    private LanguageRequest language;

    @ApiModelProperty(value = "Course type")
    private CourseTypeRequest courseType;
}

