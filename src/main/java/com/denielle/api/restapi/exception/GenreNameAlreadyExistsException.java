package com.denielle.api.restapi.exception;

public class GenreNameAlreadyExistsException extends RuntimeException {
    public GenreNameAlreadyExistsException(String message) {
        super(message);
    }
}
