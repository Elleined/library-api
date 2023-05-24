package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO) {
        int bookId = bookService.save(bookDTO);
        BookDTO fetchedBook = bookService.getById(bookId);
        
        return new ResponseEntity<>(fetchedBook, HttpStatus.CREATED);
    }
}
