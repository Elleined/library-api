package com.elleined.bookauthorgenreapi.exception;

public class FieldAlreadyExistsException extends RuntimeException {
    public FieldAlreadyExistsException(String message) {
        super(message);
    }
}
