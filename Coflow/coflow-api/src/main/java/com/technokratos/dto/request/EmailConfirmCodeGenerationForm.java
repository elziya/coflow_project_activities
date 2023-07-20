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
@ApiModel(description = "Request to generate a confirmation coder")
public class EmailConfirmCodeGenerationForm {

    @ApiModelProperty(value = "Email", example = "test@gmail.com")
    private String email;
}
