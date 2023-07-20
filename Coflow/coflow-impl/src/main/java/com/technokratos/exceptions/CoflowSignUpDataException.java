package com.technokratos.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class CoflowSignUpDataException extends CoflowIllegalArgumentException {

    private BindingResult validationResult;

    public CoflowSignUpDataException() {
        super("Illegal sign up data");
    }

    public CoflowSignUpDataException(BindingResult result) {
        super("Illegal sign up data");
        this.validationResult = result;
    }
}

