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
@ApiModel(description = "Request to join the course")
public class JoinCourseForm {

    @ApiModelProperty(value = "Access code for joining a closed course", example = "426fa08f-1944-46c2-9609-a6655938ff75")
    private UUID accessCode;
}

