package com.technokratos.validation;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ValidationResult {

    /** The name of the object that was validated */
    private String object;

    /** The value of the validation result */
    private Boolean isValid;
}
