package com.technokratos.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Dto with tokens for updating")
public class TokenCoupleDto {

    @ApiModelProperty(value = "Access token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhODhhZjNiYi0zMjhiLTRmNDEtOT" +
            "Y2Mi1kMDM0YTJkODE3MzUiLCJlbWFpbCI6InBvbGluYS5icmlnQGdtYWlsLmNvbSIsInJvbGVzIjpbIlVTRVIiXSwiaWF0IjoxNjU" +
            "zNDA1MDMxLCJleHAiOjE2NTM0MDUwMzF9.HZvQ5QIgq-ylt50CA3MMpzaAxDO6nUp_HhpNJJ3WwIiGoaeNB6JOBKpUrorp2t3zPHkMa6" +
            "iyKmyr1W_rKU6cIA")
    private String accessToken;

    @ApiModelProperty(value = "Refresh token", example = "3ee907fb-eebd-4d2f-88ae-d689460af50a")
    private String refreshToken;
}