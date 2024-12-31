package com.example.albumcollector.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// Manejador global de excepciones

@ControllerAdvice

public class GlobalExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {

        return ResponseEntity.status(404).body(ex.getMessage());

    }



    @ExceptionHandler(IllegalArgumentException.class)

    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {

        return ResponseEntity.status(400).body(ex.getMessage());

    }



    @ExceptionHandler(Exception.class)

    public ResponseEntity<?> handleGeneralException(Exception ex) {

        return ResponseEntity.status(500).body("Error interno: " + ex.getMessage());

    }

}
