package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Dto for account's information")
public class AccountExtendedResponse extends AccountResponse{

    @ApiModelProperty(value = "Email", example = "savbrig@gmail.com")
    private String email;

    @ApiModelProperty(value = "First name", example = "Polangel")
    private String firstName;

    @ApiModelProperty(value = "Last name", example = "Savbrig")
    private String lastName;

    @ApiModelProperty(value = "All account's courses")
    private List<EducationResponse> courses;
}
