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
@ApiModel(description = "Lesson creation form")
public class LessonCreationForm {

    @ApiModelProperty(value = "Name", example = "Setting up Swagger")
    private String name;

    @ApiModelProperty(value = "Description", example = "In this tutorial you will learn how to set up Swagger")
    private String description;

    @ApiModelProperty(value = "Main content")
    private String mainContent;
}

