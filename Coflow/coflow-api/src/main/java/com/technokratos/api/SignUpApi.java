package com.technokratos.api;

import com.technokratos.dto.request.SignUpForm;
import com.technokratos.dto.response.ExceptionMessage;
import com.technokratos.dto.response.LessonResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1")
public interface SignUpApi {

    @ApiOperation(value = "Sign up")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registered successfully", response = UUID.class),
            @ApiResponse(code = 400, message = "Invalid data", response = ExceptionMessage.class)
    })
    @PostMapping(value = "/sign-up",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID signUp(@RequestBody @Valid SignUpForm signUpForm);
}

