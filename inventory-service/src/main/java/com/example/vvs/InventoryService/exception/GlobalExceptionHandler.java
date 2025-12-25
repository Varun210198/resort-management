package com.example.vvs.InventoryService.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // -------------------------
    // LOCATION EXCEPTIONS
    // -------------------------
    @ExceptionHandler(LocationAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleLocationExists(
            LocationAlreadyExistsException ex, HttpServletRequest req) {

        return buildErrorResponse(ex, req, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleLocationNotFound(
            LocationNotFoundException ex, HttpServletRequest req) {

        return buildErrorResponse(ex, req, HttpStatus.NOT_FOUND);
    }


    // -------------------------
    // RESORT EXCEPTIONS
    // -------------------------
    @ExceptionHandler(ResortAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleResortExists(
            ResortAlreadyExistsException ex, HttpServletRequest req) {

        return buildErrorResponse(ex, req, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResortNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResortNotFound(
            ResortNotFoundException ex, HttpServletRequest req) {

        return buildErrorResponse(ex, req, HttpStatus.NOT_FOUND);
    }


    // -------------------------
    // ACCOMMODATION TYPE EXCEPTIONS
    // -------------------------
    @ExceptionHandler(AccommodationTypeAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleAccoTypeExists(
            AccommodationTypeAlreadyExistsException ex, HttpServletRequest req) {

        return buildErrorResponse(ex, req, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccommodationTypeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleAccoTypeNotFound(
            AccommodationTypeNotFoundException ex, HttpServletRequest req) {

        return buildErrorResponse(ex, req, HttpStatus.NOT_FOUND);
    }


    // -------------------------
    // UNIT EXCEPTIONS
    // -------------------------
    @ExceptionHandler(UnitAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleUnitExists(
            AccommodationTypeNotFoundException ex, HttpServletRequest req){
        return buildErrorResponse(ex, req, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnitNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUnitNotFound(
            AccommodationTypeNotFoundException ex, HttpServletRequest req) {
        return buildErrorResponse(ex, req, HttpStatus.NOT_FOUND);
    }

    // -------------------------
    // VALIDATION ERRORS (@Valid)
    // -------------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, HttpServletRequest req) {

        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                message,
                req.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    // -------------------------
    // GENERIC / UNKNOWN ERRORS
    // -------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericError(
            Exception ex, HttpServletRequest req) {

        return buildErrorResponse(ex, req, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // -------------------------
    // COMMON METHOD
    // -------------------------
    private ResponseEntity<ApiErrorResponse> buildErrorResponse(
            Exception ex, HttpServletRequest req, HttpStatus status) {

        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                req.getRequestURI()
        );

        return new ResponseEntity<>(response, status);
    }
}
