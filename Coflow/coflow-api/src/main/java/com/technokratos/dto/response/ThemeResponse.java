package com.technokratos.dto.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "theme response")
public class ThemeResponse {

    @ApiModelProperty(value = "Theme id")
    private UUID id;

    @ApiModelProperty(value = "Name", example = "REST API")
    private String name;

    @ApiModelProperty(value = "Course")
    private CourseResponse course;
}

