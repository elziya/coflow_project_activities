package com.technokratos.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

import static com.technokratos.consts.CoflowCommonConstants.MAX_STARS;
import static com.technokratos.consts.CoflowCommonConstants.MIN_STARS;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Request to add feedback with the number of stars")
public class FeedbackForm {

    @NotNull
    @ApiModelProperty(value = "Text", example = "Nice course!")
    private String text;

    @NotNull
    @Range(min = MIN_STARS, max = MAX_STARS)
    @ApiModelProperty(value = "Number of stars", example = "5")
    private Integer estimation;
}

