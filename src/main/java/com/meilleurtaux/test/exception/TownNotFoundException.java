package com.meilleurtaux.test.exception;

public class TownNotFoundException extends RuntimeException {

    public TownNotFoundException(String message) {
        super(message);
    }
}
