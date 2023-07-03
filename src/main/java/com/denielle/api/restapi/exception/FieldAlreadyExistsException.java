package com.denielle.api.restapi.exception;

public class FieldAlreadyExistsException extends RuntimeException {
    public FieldAlreadyExistsException(String message) {
        super(message);
    }
}
