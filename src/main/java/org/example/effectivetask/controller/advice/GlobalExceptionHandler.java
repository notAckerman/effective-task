package org.example.effectivetask.controller.advice;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.example.effectivetask.model.dto.ErrorResponse;
import org.example.effectivetask.model.dto.FailResponse;
import org.example.effectivetask.util.exception.AccessDeniedException;
import org.example.effectivetask.util.exception.BadCredentialsException;
import org.example.effectivetask.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public FailResponse<String> handleNotFoundException(NotFoundException e) {
        return new FailResponse<>("fail", e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public FailResponse<String> handleAccessDeniedException(AccessDeniedException e) {
        return new FailResponse<>("fail", e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public FailResponse<String> handleBadCredentialsException(BadCredentialsException e) {
        return new FailResponse<>("fail", e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FailResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new FailResponse<>("fail", ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage
                )));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.error(e.getMessage());
        return new ErrorResponse("error", "Internal error.");
    }
}
