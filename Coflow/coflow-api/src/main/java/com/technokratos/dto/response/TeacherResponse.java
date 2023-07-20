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
@ApiModel(description = "Dto with short information about teacher")
public class TeacherResponse {

    @ApiModelProperty(value = "ID", example = "3ee907fb-eebd-4d2f-88ae-d689460af50a")
    private UUID id;

    @ApiModelProperty(value = "Information about teacher", example = "Graduated from KFU, ITIS \"software engineering\"" +
            " in 2018")
    private String info;

    @ApiModelProperty(value = "Teacher's account")
    private AccountResponse account;
}
