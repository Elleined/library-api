package com.elleined.libraryapi.exception;

public class FieldAlreadyExistsException extends RuntimeException {
    public FieldAlreadyExistsException(String message) {
        super(message);
    }
}
