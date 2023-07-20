package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Dto for page with courses")
public class CoursesPage {

    @ApiModelProperty(value = "Courses on current page")
    private List<SearchCourseResponse> courses;

    @ApiModelProperty(value = "Total amount of available pages with courses", example = "5")
    private Integer totalPages;
}
