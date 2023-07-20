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
@ApiModel(description = "Course type response")
public class EducationCategoryResponse {

    @ApiModelProperty(value = "Education category id")
    private UUID id;

    @ApiModelProperty(value = "Education category name", example = "PROCESS")
    private String name;
}
