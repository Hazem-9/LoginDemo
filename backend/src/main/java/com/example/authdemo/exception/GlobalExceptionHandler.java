package com.example.authdemo.exception;

import com.example.authdemo.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> badCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiError("AUTH_001", "Invalid username or password", Instant.now()));
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ApiError> disabled() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiError("AUTH_002", "User account is disabled", Instant.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");
        return ResponseEntity.badRequest().body(new ApiError("VALIDATION_ERROR", message, Instant.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> general(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError("INTERNAL_ERROR", "Something went wrong", Instant.now()));
    }
}
