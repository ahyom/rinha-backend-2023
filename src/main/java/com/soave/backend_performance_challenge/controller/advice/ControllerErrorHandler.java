package com.soave.backend_performance_challenge.controller.advice;

import com.soave.backend_performance_challenge.model.dto.ApiErrorMessage;
import com.soave.backend_performance_challenge.model.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class ControllerErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerErrorHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.debug("Handling MethodArgumentNotValidException");
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, errors);
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
        log.error("Handling UserNotFoundException");
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }
}
