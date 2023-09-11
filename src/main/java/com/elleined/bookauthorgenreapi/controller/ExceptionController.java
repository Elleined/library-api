package com.elleined.bookauthorgenreapi.controller;

import com.elleined.bookauthorgenreapi.dto.ResponseMessage;
import com.elleined.bookauthorgenreapi.exception.FieldAlreadyExistsException;
import com.elleined.bookauthorgenreapi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessage> handleNotFoundException(NotFoundException ex) {
        var responseMessage = new ResponseMessage(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({FieldAlreadyExistsException.class, IllegalArgumentException.class})
    public ResponseEntity<ResponseMessage> handleGenreNameAlreadyExistsException(RuntimeException ex) {
        var responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ResponseMessage>> handleBindException(BindException e) {
        List<ResponseMessage> errors = e.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .map(errorMessage -> new ResponseMessage(HttpStatus.BAD_REQUEST, errorMessage))
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
