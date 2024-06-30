package com.elleined.libraryapi.controller;

import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.mapper.BookMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.service.author.AuthorService;
import com.elleined.libraryapi.service.book.BookService;
import com.elleined.libraryapi.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    private final GenreService genreService;

    private final AuthorService authorService;

    @GetMapping
    public List<BookDTO> getAll() {
        return bookService.getAll().stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable("id") int bookId) {
        Book book = bookService.getById(bookId);
        return bookMapper.toDTO(book);
    }

    @GetMapping("/get-all-by-id")
    public List<BookDTO> getAllById(@RequestParam("ids") List<Integer> bookIds) {
        return bookService.getAllById(bookIds).stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @GetMapping("/title")
    public List<BookDTO> getAllByTitleFirstLetter(@RequestParam("firstLetter") char firstLetter) {
        return bookService.getAllByTitleFirstLetter(firstLetter, ).stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @GetMapping("/isbn/{isbn}")
    public BookDTO getByIsbn(@PathVariable("isbn") String isbn) {
        Book book = bookService.getByIsbn(isbn);
        return bookMapper.toDTO(book);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public List<BookDTO> getAll(@PathVariable int pageNumber,
                                @PathVariable int pageSize) {

        return bookService.getAll(pageNumber, pageSize).stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @GetMapping("/{pageNumber}/{pageSize}/{sortDirection}/{sortProperty}")
    public List<BookDTO> getAll(@PathVariable int pageNumber,
                                @PathVariable int pageSize,
                                @PathVariable String sortDirection,
                                @PathVariable String sortProperty) {

        return bookService.getAll(pageNumber, pageSize, sortDirection, sortProperty).stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @PostMapping
    public BookDTO save(@RequestParam("title") String title,
                        @RequestParam("isbn") String isbn,
                        @RequestParam("description") String description,
                        @RequestParam("publishedDate") LocalDate publishedDate,
                        @RequestParam("pages") int pages,
                        @RequestParam("authorId") int authorId,
                        @RequestParam("genres") Set<Integer> genreIds) {

        Author author = authorService.getById(authorId);
        Set<Genre> genres = genreService.getAllById(genreIds);
        Book book = bookService.save(title, isbn, description, publishedDate, pages, author, genres);
        return bookMapper.toDTO(book);
    }

    @PutMapping("/{id}")
    public BookDTO update(@PathVariable("id") int bookId,
                          @RequestParam("title") String title,
                          @RequestParam("isbn") String isbn,
                          @RequestParam("description") String description,
                          @RequestParam("publishedDate") LocalDate publishedDate,
                          @RequestParam("pages") int pages,
                          @RequestParam("authorId") int authorId,
                          @RequestParam("genres") Set<Integer> genreIds) {

        Author author = authorService.getById(authorId);
        Set<Genre> genres = genreService.getAllById(genreIds);
        Book book = bookService.getById(bookId);
        bookService.update(book, title, isbn, description, publishedDate, pages, author, genres);
        return bookMapper.toDTO(book);
    }
}