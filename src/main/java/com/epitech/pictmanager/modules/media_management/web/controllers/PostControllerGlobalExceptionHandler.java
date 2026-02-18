package com.epitech.pictmanager.modules.media_management.web.controllers;

import com.epitech.pictmanager.modules.media_management.application.exceptions.MediaNotFoundException;
import com.epitech.pictmanager.modules.media_management.application.exceptions.MediaPermissionException;
import com.epitech.pictmanager.shared.responses.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class PostControllerGlobalExceptionHandler {
    @ExceptionHandler(MediaNotFoundException.class)
    public ResponseEntity<ApiError> handleMediaNotFoundException(MediaNotFoundException ex, HttpServletRequest req) {
        return new ResponseEntity<>(new ApiError(ex.getCode(), ex.getMessage(), req.getRequestURI(), Instant.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MediaPermissionException.class)
    public ResponseEntity<ApiError> handleMediaPermissionException(MediaPermissionException ex, HttpServletRequest req) {
        return new ResponseEntity<>(new ApiError(ex.getCode(), ex.getMessage(), req.getRequestURI(), Instant.now()), HttpStatus.UNAUTHORIZED);
    }
}
