package com.freelance.kiosk_backend.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle Patient Not Found Exception
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePatientNotFoundException(PatientNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Patient Not Found Exception", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Handle Invalid Test Name Exception
    @ExceptionHandler(InvalidTestNameException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTestNameException(InvalidTestNameException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid Test Name Exception", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // Handle Invalid Test Range Exception
    @ExceptionHandler(InvalidTestRangeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTestRangeException(InvalidTestRangeException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid Test Range Exception", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid Request Exception", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // General Exception Handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}