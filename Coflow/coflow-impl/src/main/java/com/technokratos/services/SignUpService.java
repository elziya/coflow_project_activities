package com.technokratos.services;

import com.technokratos.dto.request.SignUpForm;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface SignUpService {

    UUID signUp(SignUpForm signUpForm);
}

