package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.ResponseMessage;
import com.denielle.api.restapi.exception.AuthorNotFoundException;
import com.denielle.api.restapi.exception.BookNotFoundException;
import com.denielle.api.restapi.exception.genre.GenreNameAlreadyExistsException;
import com.denielle.api.restapi.exception.genre.GenreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({GenreNotFoundException.class, AuthorNotFoundException.class, BookNotFoundException.class})
    public ResponseEntity<ResponseMessage> handleNotFoundException(RuntimeException ex) {
        var responseMessage = new ResponseMessage(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenreNameAlreadyExistsException.class)
    public ResponseEntity<ResponseMessage> handleGenreNameAlreadyExistsException(GenreNameAlreadyExistsException ex) {
        var responseMessage = new ResponseMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
    }
}
