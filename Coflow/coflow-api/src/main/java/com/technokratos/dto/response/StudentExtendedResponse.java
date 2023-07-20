package com.technokratos.dto.response;

import com.technokratos.dto.enums.EducationCategory;
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
@ApiModel(description = "Course type response")
public class StudentExtendedResponse extends AccountResponse {

    @ApiModelProperty(value = "First name", example = "Polangel")
    private String firstName;

    @ApiModelProperty(value = "Last name", example = "Savbrig")
    private String lastName;

    @ApiModelProperty(value = "Email", example = "test@gmail.com")
    private String email;

    @ApiModelProperty(value = "Education category", example = "PROCESS")
    private EducationCategory educationCategory;
}

