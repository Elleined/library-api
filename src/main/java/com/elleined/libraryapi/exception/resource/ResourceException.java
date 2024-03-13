package com.elleined.libraryapi.exception.resource;

import com.elleined.libraryapi.exception.LibraryAPIException;

public class ResourceException extends LibraryAPIException {
    public ResourceException(String message) {
        super(message);
    }
}
