package com.meilleurtaux.test.exception;

public class InvalidPostalCodeException extends RuntimeException {

    public InvalidPostalCodeException(String message) {
        super(message);
    }
}
