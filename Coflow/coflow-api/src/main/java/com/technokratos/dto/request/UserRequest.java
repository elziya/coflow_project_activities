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
@ApiModel(description = "Dto for login")
public class UserRequest {

    @ApiModelProperty(value = "Email", example = "savbrig@gmail.com")
    private String email;

    @ApiModelProperty(value = "Password", example = "as%wer9QW?")
    private String password;
}
