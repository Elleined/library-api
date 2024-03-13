package com.elleined.libraryapi.controller;

import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
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
    public BookDTO save(@Valid @RequestBody BookDTO bookDTO) {
        return bookService.save(bookDTO);
    }

    @PostMapping("/save-all")
    public List<BookDTO> save(@RequestBody List<BookDTO> books) {
        return bookService.saveAll(books);
    }

    @PutMapping("/{id}")
    public BookDTO update(@PathVariable("id") int bookId,
                                    @Valid @RequestBody BookDTO bookDTO) {

        bookService.update(bookId, bookDTO);
        return bookService.getById(bookId);
    }
}