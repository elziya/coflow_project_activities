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
@ApiModel(description = "Dto for updating teacher's information and creating")
public class TeacherRequest {

    @ApiModelProperty(value = "Information about teacher", example = "Graduated from KFU, ITIS \"software engineering\"" +
            " in 2018")
    private String info;

}
