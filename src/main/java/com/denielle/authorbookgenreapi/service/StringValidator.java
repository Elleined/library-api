package com.denielle.authorbookgenreapi.service;

public interface StringValidator {

    static boolean validate(String field) {
        return field == null || field.isBlank() || field.isEmpty();
    }
}
