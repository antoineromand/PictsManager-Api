package com.epitech.pictmanager.modules.user_management.web.controllers;

import com.epitech.pictmanager.modules.user_management.application.exceptions.ProfileNotFoundException;
import com.epitech.pictmanager.shared.responses.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice(basePackages = "com.epitech.pictmanager.modules.user_management.web.controllers")
public class UserManagementGlobalHandlerException {
    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<ApiError> handleProfileNotFoundException(ProfileNotFoundException ex, HttpServletRequest request) {
        ApiError error = new ApiError(
                ex.getCode(), ex.getMessage(), request.getRequestURI(), Instant.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
