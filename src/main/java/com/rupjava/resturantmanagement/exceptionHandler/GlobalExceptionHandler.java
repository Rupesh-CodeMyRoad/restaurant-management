package com.rupjava.resturantmanagement.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle ResourceNotFoundException.
     *
     * @param ex Exception instance.
     * @return ResponseEntity with error message.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle NoAvailableTableException.
     *
     * @param ex Exception instance.
     * @return ResponseEntity with error message.
     */
    @ExceptionHandler(NoAvailableTableException.class)
    public ResponseEntity<ErrorResponse> handleNoAvailableTable(NoAvailableTableException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Handle NoAvailableTableException.
     *
     * @param ex Exception instance.
     * @return ResponseEntity with error message.
     */
    @ExceptionHandler(InvalidTableCapacityException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTableCapacityException(InvalidTableCapacityException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Handle generic Exception.
     *
     * @param ex Exception instance.
     * @return ResponseEntity with error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ErrorResponse class
    static class ErrorResponse {
        private int status;
        private String message;

        public ErrorResponse() {
        }

        public ErrorResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }
}
