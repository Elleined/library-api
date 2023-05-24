package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.ResponseMessage;
import com.denielle.api.restapi.exception.NameAlreadyExistsException;
import com.denielle.api.restapi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessage> handleNotFoundException(NotFoundException ex) {
        var responseMessage = new ResponseMessage(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameAlreadyExistsException.class)
    public ResponseEntity<ResponseMessage> handleGenreNameAlreadyExistsException(NameAlreadyExistsException ex) {
        var responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ResponseMessage> handleNotDeletableException(SQLIntegrityConstraintViolationException e) {
        var responseMessage = new ResponseMessage(HttpStatus.CONFLICT, e.getMessage() + "You cannot delete a record associated with another record", LocalDateTime.now());
        return new ResponseEntity<>(responseMessage, HttpStatus.CONFLICT);
    }
}
