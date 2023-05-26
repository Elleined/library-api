package com.denielle.api.restapi.controller;

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

    @GetMapping("/get-all-by-id")
    public List<BookDTO> getAllById(@RequestParam("ids") List<Integer> bookIds) {
        return bookService.getAllById(bookIds);
    }

    @GetMapping("/title/{title}")
    public BookDTO getByTitle(@PathVariable("title") String bookTitle) {
        return bookService.getByTitle(bookTitle);
    }

    @GetMapping("/title")
    public List<String> searchByFirstLetter(@RequestParam("firstLetter") char firstLetter) {
        return bookService.searchByFirstLetter(firstLetter);
    }
    
    @GetMapping("/genre/{genreName}")
    public List<BookDTO> getAllByGenre(@PathVariable("genreName") String genreName) {
        return bookService.getAllByGenre(genreName);
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

    @PostMapping("/save-all")
    public ResponseEntity<List<BookDTO>> save(@RequestBody List<BookDTO> books) {
        List<Integer> bookIds = bookService.saveAll(books);
        List<BookDTO> fetchedBooks = bookService.getAllById(bookIds);

        return new ResponseEntity<>(fetchedBooks, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") int bookId,
                                          @RequestBody BookDTO bookDTO) {

        bookService.update(bookId, bookDTO);

        BookDTO fetchedBookDTO = bookService.getById(bookId);
        return ResponseEntity.ok(fetchedBookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> delete(@PathVariable("id") int bookId) {
        bookService.delete(bookId);
        return ResponseEntity.noContent().build();
    }
}
