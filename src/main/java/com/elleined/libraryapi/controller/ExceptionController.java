package com.elleined.libraryapi.controller;

import com.elleined.libraryapi.dto.APIResponse;
import com.elleined.libraryapi.exception.LibraryAPIException;
import com.elleined.libraryapi.exception.resource.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<APIResponse> handleResourceException(ResourceException ex) {
        var responseMessage = new APIResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({
            LibraryAPIException.class,
    })
    public ResponseEntity<APIResponse> handleSystemException(RuntimeException ex) {
        var responseMessage = new APIResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<APIResponse>> handleBindException(BindException e) {
        List<APIResponse> errors = e.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .map(errorMessage -> new APIResponse(HttpStatus.BAD_REQUEST, errorMessage))
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
