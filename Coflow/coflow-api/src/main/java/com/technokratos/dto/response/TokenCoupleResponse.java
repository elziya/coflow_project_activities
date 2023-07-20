package com.technokratos.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Dto with tokens")
public class TokenCoupleResponse {

    @ApiModelProperty(value = "Access token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhODhhZjNiYi0zMjhiLTRmNDEtOT" +
            "Y2Mi1kMDM0YTJkODE3MzUiLCJlbWFpbCI6InBvbGluYS5icmlnQGdtYWlsLmNvbSIsInJvbGVzIjpbIlVTRVIiXSwiaWF0IjoxNjU" +
            "zNDA1MDMxLCJleHAiOjE2NTM0MDUwMzF9.HZvQ5QIgq-ylt50CA3MMpzaAxDO6nUp_HhpNJJ3WwIiGoaeNB6JOBKpUrorp2t3zPHkMa6" +
            "iyKmyr1W_rKU6cIA")
    private String accessToken;

    @ApiModelProperty(value = "Refresh token", example = "3ee907fb-eebd-4d2f-88ae-d689460af50a")
    private String refreshToken;

    @ApiModelProperty(value = "Expiration date of access token", example = "1653405031000")
    private Date accessTokenExpirationDate;
}
