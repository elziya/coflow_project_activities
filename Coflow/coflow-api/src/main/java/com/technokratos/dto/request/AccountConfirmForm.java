package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Form for account confirmation by email")
public class AccountConfirmForm {

    @ApiModelProperty(value = "Email", example = "test@gmail.com")
    private String email;

    @ApiModelProperty(value = "Confirmation code", example = "66a8fd4b-0439-4f40-bf68-98d453b59ee2")
    private UUID code;
}

