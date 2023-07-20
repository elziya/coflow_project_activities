package com.technokratos.api;

import com.technokratos.dto.TokenCoupleDto;
import com.technokratos.dto.response.AccountExtendedResponse;
import com.technokratos.dto.response.ExceptionMessage;
import com.technokratos.dto.response.TokenCoupleResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/token")
public interface JwtTokenApi {

    @ApiOperation(value = "Update access and refresh tokens")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got new access and refresh tokens",
                    response = AccountExtendedResponse.class),
            @ApiResponse(code = 401, message = "User not found with token or the refresh token has expired or" +
                    " refresh token doesn't exist",
                    response = ExceptionMessage.class),
    })
    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse updateTokens(@RequestBody TokenCoupleDto tokenCoupleDto);
}
