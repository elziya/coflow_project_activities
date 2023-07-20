package com.technokratos.api;

import com.technokratos.dto.request.AccountConfirmForm;
import com.technokratos.dto.request.EmailConfirmCodeGenerationForm;
import com.technokratos.dto.response.ExceptionMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/confirm")
public interface ConfirmApi {

    @ApiOperation(value = "Account email confirmation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Confirmed account successfully", response = Boolean.class),
            @ApiResponse(code = 400, message = "Expired or illegal confirm code or already confirmed account", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Not existing account", response = ExceptionMessage.class)
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Boolean confirmAccount(@RequestBody AccountConfirmForm accountConfirmForm);

    @ApiOperation(value = "Generate new account confirm code")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Generated code successfully", response = UUID.class),
            @ApiResponse(code = 400, message = "Already existed confirm code or already confirmed account", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Not existing account", response = ExceptionMessage.class)
    })
    @PostMapping(value = "/confirm-code", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UUID generateAccountConfirmCode(@RequestBody EmailConfirmCodeGenerationForm form);
}

