package com.technokratos.controllers.handlers;

import com.technokratos.dto.response.ExceptionMessage;
import com.technokratos.exceptions.CoflowBadCredentials;
import com.technokratos.exceptions.CoflowServiceException;
import com.technokratos.validation.FieldValidationResult;
import com.technokratos.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

import static com.technokratos.consts.CoflowMessages.LOGIN_BAD_CREDENTIALS_EXCEPTION_MESSAGE;
import static com.technokratos.consts.CoflowMessages.SIGNUP_FORM_VALIDATION_ERROR_MESSAGE;

/**
 * Error handler
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Generates a response based on all exceptions {@link Exception}.
     *
     * @param exception exception
     * @return exception-based response
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionMessage> onAllExceptions(Exception exception) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .exceptionName(exception.getClass().getSimpleName())
                        .build());
    }


    /**
     * Generates a response based on all project exceptions {@link CoflowServiceException}.
     *
     * @param coflowServiceException project exceptions
     * @return exception-based response
     */
    @ExceptionHandler(CoflowServiceException.class)
    public final ResponseEntity<ExceptionMessage> onAccountExceptionExceptions(CoflowServiceException coflowServiceException) {

        return ResponseEntity.status(coflowServiceException.getHttpStatus())
                .body(ExceptionMessage.builder()
                        .message(coflowServiceException.getMessage())
                        .exceptionName(coflowServiceException.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ValidationExceptionMessage> onMethodArgumentNotValidExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ValidationExceptionMessage.builder()
                        .message(SIGNUP_FORM_VALIDATION_ERROR_MESSAGE)
                        .exceptionName(ex.getClass().getSimpleName())
                        .failedValidationResults(formFieldValidationErrors(ex.getFieldErrors()))
                        .build());
    }

    private List<ValidationResult> formFieldValidationErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(e -> FieldValidationResult.builder()
                        .object(e.getObjectName())
                        .rejectedFieldMessageError(e.getDefaultMessage())
                        .rejectedField(e.getField())
                        .rejectedFieldValue(String.valueOf(e.getRejectedValue()))
                        .build())
                .collect(Collectors.toList());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<ExceptionMessage> onNoHandlerFoundExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .exceptionName(exception.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionMessage> onAccessDeniedExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionMessage.builder()
                        .message(exception.getMessage())
                        .exceptionName(exception.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionMessage> onBadCredentialsExceptions(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionMessage.builder()
                        .message(LOGIN_BAD_CREDENTIALS_EXCEPTION_MESSAGE)
                        .exceptionName(CoflowBadCredentials.class.getSimpleName())
                        .build());
    }
}

