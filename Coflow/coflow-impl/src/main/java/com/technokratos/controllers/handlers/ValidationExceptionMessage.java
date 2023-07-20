package com.technokratos.controllers.handlers;

import com.technokratos.dto.response.ExceptionMessage;
import com.technokratos.validation.ValidationResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ValidationExceptionMessage extends ExceptionMessage {

    /** List of failed data validations */
   private List<ValidationResult> failedValidationResults;

}

