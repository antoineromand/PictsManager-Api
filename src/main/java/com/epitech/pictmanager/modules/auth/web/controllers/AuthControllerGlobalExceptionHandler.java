package com.epitech.pictmanager.modules.auth.web.controllers;

import com.epitech.pictmanager.modules.auth.application.exceptions.UsernameOrEmailAlreadyTakenException;
import com.epitech.pictmanager.shared.responses.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice(basePackages = "com.epitech.pictmanager.modules.auth.web.controllers")
public class AuthControllerGlobalExceptionHandler {
    @ExceptionHandler(UsernameOrEmailAlreadyTakenException.class)
    public ResponseEntity<ApiError> handleUsernameOrEmailAlreadyTakenException(UsernameOrEmailAlreadyTakenException ex, HttpServletRequest request) {
        ApiError error = new ApiError(ex.getCode(), ex.getMessage(), request.getRequestURI(), Instant.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
