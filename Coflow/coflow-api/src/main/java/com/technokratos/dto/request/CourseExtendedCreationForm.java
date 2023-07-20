package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Course info extended request")
public class CourseExtendedCreationForm extends CourseCreationForm {

    @ApiModelProperty(value = "Description")
    private String description;

    @ApiModelProperty(value = "For whom", example = "For beginners")
    private String forWhom;
}

