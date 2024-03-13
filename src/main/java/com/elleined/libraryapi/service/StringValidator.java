package com.elleined.bookauthorgenreapi.service;

public interface StringValidator {

    static boolean validate(String field) {
        return field == null || field.isBlank() || field.isEmpty();
    }
}
