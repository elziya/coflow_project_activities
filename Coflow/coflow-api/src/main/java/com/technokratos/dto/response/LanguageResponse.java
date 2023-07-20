package com.technokratos.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Language response")
public class LanguageResponse {

    @ApiModelProperty(value = "Language id")
    private UUID id;

    @ApiModelProperty(value = "Language", example = "RU")
    private String name;
}
