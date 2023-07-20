package com.technokratos.validation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class FieldValidationResult extends ValidationResult {

    /** The error message text for this rejected field */
    private String rejectedFieldMessageError;

    /** The name of the object field that was rejected */
    private String rejectedField;

    /** The value of the object field that was rejected */
    private String rejectedFieldValue;
}
