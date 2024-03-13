package com.elleined.libraryapi.service;

public interface StringValidator {

    static boolean validate(String field) {
        return field == null || field.isBlank() || field.isEmpty();
    }
}
