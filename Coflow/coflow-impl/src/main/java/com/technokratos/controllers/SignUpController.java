package com.technokratos.controllers;

import com.technokratos.api.SignUpApi;
import com.technokratos.dto.request.SignUpForm;
import com.technokratos.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class SignUpController implements SignUpApi {

    private final SignUpService signUpService;

    @Override
    public UUID signUp(SignUpForm signUpForm) {
        return signUpService.signUp(signUpForm);
    }
}

