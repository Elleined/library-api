package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.ResponseMessage;
import com.denielle.api.restapi.exception.GenreNameAlreadyExistsException;
import com.denielle.api.restapi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseMessage> handleNotFoundException(NotFoundException ex) {
        var responseMessage = new ResponseMessage(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenreNameAlreadyExistsException.class)
    public ResponseEntity<ResponseMessage> handleGenreNameAlreadyExistsException(GenreNameAlreadyExistsException ex) {
        var responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
}
