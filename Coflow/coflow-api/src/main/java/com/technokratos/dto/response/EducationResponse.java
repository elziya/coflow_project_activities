package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Dto with information about course and it's category")
public class EducationResponse {

    @ApiModelProperty(value = "Course")
    private CourseResponse course;

    @ApiModelProperty(value = "Courses category")
    private EducationCategoryResponse category;

}
