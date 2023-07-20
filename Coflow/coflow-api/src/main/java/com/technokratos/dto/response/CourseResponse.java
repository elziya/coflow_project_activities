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
@ApiModel(description = "Dto for course's short information")
public class CourseResponse {

    @ApiModelProperty(value = "ID", example = "3ee907fb-eebd-4d2f-88ae-d689460af50a")
    private UUID id;

    @ApiModelProperty(value = "Name", example = "Java Programming")
    private String name;

    @ApiModelProperty(value = "Information about finishing course", example = "true")
    private Boolean isFinished;

    @ApiModelProperty(value = "Language")
    private LanguageResponse language;

    @ApiModelProperty(value = "Course's type")
    private CourseTypeResponse courseType;
}
