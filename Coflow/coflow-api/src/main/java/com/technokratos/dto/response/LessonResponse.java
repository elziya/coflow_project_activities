package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Lesson response")
public class LessonResponse {

    @ApiModelProperty(value = "Lesson id")
    private UUID id;

    @ApiModelProperty(value = "Name", example = "Setting up Swagger")
    private String name;

    @ApiModelProperty(value = "Description", example = "In this tutorial you will learn how to set up Swagger")
    private String description;

    @ApiModelProperty(value = "Main content")
    private String mainContent;

    @ApiModelProperty(value = "Theme")
    private ThemeResponse theme;

    @ApiModelProperty(value = "File attachments")
    private Set<FileInfoResponse> materials;
}

