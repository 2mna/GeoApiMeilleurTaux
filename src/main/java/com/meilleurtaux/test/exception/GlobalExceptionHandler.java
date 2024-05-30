package com.meilleurtaux.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TownNotFoundException.class)
    public ResponseEntity<Response> handleTownNotFoundException(TownNotFoundException ex) {
        Response response = new Response(ex.getMessage(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<Response> handleExternalServiceException(ExternalServiceException ex) {
        Response response = new Response(ex.getMessage(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneralException(Exception ex) {
        Response response = new Response(ex.getMessage(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidPostalCodeException.class)
    public ResponseEntity<Response> handleInvalidPostalCodeException(InvalidPostalCodeException ex) {
        Response response = new Response(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
