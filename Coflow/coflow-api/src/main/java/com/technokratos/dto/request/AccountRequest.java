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
@ApiModel(description = "Dto with account's id")
public class AccountRequest {

    @ApiModelProperty(value = "ID", example = "3ee907fb-eebd-4d2f-88ae-d689460af50a")
    private UUID id;
}
