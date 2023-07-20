package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Dto for updating account's names")
public class AccountNamesRequest {

    @Size(min = 2, max = 55, message = "Minimum length of first name: {min}, Maximum: {max}")
    @Pattern(regexp = "^[a-zA-Z-\\s]{2,55}$", message = "First name must match the {regexp} pattern")
    @NotBlank(message = "The first name can't be empty")
    @ApiModelProperty(value = "First name", example = "Polangel")
    private String firstName;

    @Size(min = 2, max = 55, message = "Minimum length of last name: {min}, Maximum: {max}")
    @Pattern(regexp = "^[a-zA-Z-\\s]{2,55}$", message = "Last name must match the {regexp} pattern")
    @NotBlank(message = "The last name can't be empty")
    @ApiModelProperty(value = "Last name", example = "Savbrig")
    private String lastName;
}
