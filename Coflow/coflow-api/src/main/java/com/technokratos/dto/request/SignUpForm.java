package com.technokratos.dto.request;

import com.technokratos.validation.ValidPassword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ValidPassword
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ApiModel(description = "Sign up form")
public class SignUpForm {

    @Email
    @NotBlank(message = "The email can't be empty")
    @ApiModelProperty(value = "Email", example = "test@gmail.com")
    private String email;

    @NotBlank(message = "The password can't be empty")
    @ApiModelProperty(value = "Password", example = "jTu7nQt?ncsu120")
    private String password;

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

