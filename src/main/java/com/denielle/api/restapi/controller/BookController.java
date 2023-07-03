package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.service.BookService;
import com.denielle.api.restapi.service.StringValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
    public List<BookDTO> getAllByTitleFirstLetter(@RequestParam("firstLetter") char firstLetter) {
        return bookService.getAllByTitleFirstLetter(firstLetter);
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

    @GetMapping("/{pageNumber}/{pageSize}/{sortDirection}/{sortProperty}")
    public List<BookDTO> getAll(@PathVariable int pageNumber,
                                @PathVariable int pageSize,
                                @PathVariable String sortDirection,
                                @PathVariable String sortProperty) {

        return bookService.getAll(pageNumber, pageSize, sortDirection, sortProperty);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody BookDTO bookDTO,
                                  BindingResult result) {

        boolean isGenreNotValid = bookDTO.getGenres()
                .stream()
                .anyMatch(StringValidator::validate);

        if (isGenreNotValid) return ResponseEntity.badRequest().body("Genre cannot be null or empty or blank");

        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }

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