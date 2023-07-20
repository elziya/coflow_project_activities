package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Course info extended response")
public class CourseExtendedResponse extends CourseResponse {

    @ApiModelProperty(value = "Teachers of this course")
    private Set<TeacherResponse> teachers;

    @ApiModelProperty(value = "Students of this course")
    private Set<StudentExtendedResponse> students;
}

