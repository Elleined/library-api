package com.denielle.api.restapi.exception.genre;

public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException(String message) {
        super(message);
    }
}
