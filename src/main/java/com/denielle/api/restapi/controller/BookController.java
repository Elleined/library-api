package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookDTO> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable("id") int bookId) {
        return bookService.getById(bookId);
    }

    @GetMapping("/title/{title}")
    public BookDTO getByTitle(@PathVariable("title") String bookTitle) {
        return bookService.getByTitle(bookTitle);
    }

    @GetMapping("/isbn/{isbn}")
    public BookDTO getByIsbn(@PathVariable("isbn") String isbn) {
        return bookService.getByIsbn(isbn);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public List<BookDTO> getAll(@PathVariable int pageNumber,
                                  @PathVariable int pageSize) {

        return bookService.getAll(pageNumber, pageSize);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{sortProperty}")
    public List<BookDTO> getAll(@PathVariable int pageNumber,
                                  @PathVariable int pageSize,
                                  @PathVariable String sortProperty) {

        return bookService.getAll(pageNumber, pageSize, Sort.Direction.ASC, sortProperty);
    }

    @PostMapping
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO) {
        int bookId = bookService.save(bookDTO);
        BookDTO fetchedBook = bookService.getById(bookId);

        return new ResponseEntity<>(fetchedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") int bookId,
                                          @RequestBody BookDTO bookDTO) {

        bookService.update(bookId, bookDTO);

        BookDTO fetchedBookDTO = bookService.getById(bookId);
        return ResponseEntity.ok(fetchedBookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDTO> delete(@PathVariable("id") int bookId) {
        bookService.delete(bookId);
        return ResponseEntity.noContent().build();
    }
}
