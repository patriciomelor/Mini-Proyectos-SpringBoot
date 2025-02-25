package com.example.albumcollector.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Excepción personalizada para recursos no encontrados
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "ResourceNotFoundException []";
    }
}