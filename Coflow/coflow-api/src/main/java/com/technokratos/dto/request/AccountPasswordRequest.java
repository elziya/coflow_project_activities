package com.technokratos.dto.request;

import com.technokratos.validation.ValidPassword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ValidPassword
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Dto for updating account's password")
public class AccountPasswordRequest {

    @ApiModelProperty(value = "Password", example = "as%wer9QW?")
    private String password;
}
