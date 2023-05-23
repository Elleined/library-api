package com.denielle.api.restapi.exception.genre;

public class GenreNameAlreadyExistsException extends RuntimeException {
    public GenreNameAlreadyExistsException(String message) {
        super(message);
    }
}
