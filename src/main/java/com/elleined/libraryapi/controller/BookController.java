package com.elleined.libraryapi.controller;

import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.hateoas.BookHateoasAssembler;
import com.elleined.libraryapi.mapper.BookMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.service.author.AuthorService;
import com.elleined.libraryapi.service.book.BookService;
import com.elleined.libraryapi.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    private final GenreService genreService;

    private final AuthorService authorService;

    private final BookHateoasAssembler bookHateoasAssembler;

    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable("id") int id,
                           @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Book book = bookService.getById(id);

        BookDTO bookDTO = bookMapper.toDTO(book);
        bookHateoasAssembler.addLinks(bookDTO, includeRelatedLinks);
        return bookDTO;
    }

    @GetMapping("/isbn/{isbn}")
    public BookDTO getByIsbn(@PathVariable("isbn") String isbn,
                             @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Book book = bookService.getByIsbn(isbn);

        BookDTO bookDTO = bookMapper.toDTO(book);
        bookHateoasAssembler.addLinks(bookDTO, includeRelatedLinks);
        return bookDTO;
    }

    @PostMapping
    public BookDTO save(@RequestParam("title") String title,
                        @RequestParam("isbn") String isbn,
                        @RequestParam("description") String description,
                        @RequestParam("publishedDate") LocalDate publishedDate,
                        @RequestParam("pages") int pages,
                        @RequestParam("authorId") int authorId,
                        @RequestParam("genres") Set<Integer> genreIds,
                        @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Author author = authorService.getById(authorId);
        Set<Genre> genres = genreService.getAllById(genreIds);
        Book book = bookService.save(title, isbn, description, publishedDate, pages, author, genres);

        BookDTO bookDTO = bookMapper.toDTO(book);
        bookHateoasAssembler.addLinks(bookDTO, includeRelatedLinks);
        return bookDTO;
    }


    @PutMapping("/{id}")
    public BookDTO update(@PathVariable("id") int id,
                          @RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("pages") int pages,
                          @RequestParam("genres") Set<Integer> genreIds,
                          @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Book book = bookService.getById(id);

        Set<Genre> genres = genreService.getAllById(genreIds);
        bookService.update(book, title, description, pages, genres);

        BookDTO bookDTO = bookMapper.toDTO(book);
        bookHateoasAssembler.addLinks(bookDTO, includeRelatedLinks);
        return bookDTO;
    }

    @GetMapping
    public Page<BookDTO> getAll(@RequestParam("authorId") int authorId,
                                @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Author author = authorService.getById(authorId);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);

        Page<BookDTO> bookDTOS = bookService.getAll(author, pageable).map(bookMapper::toDTO);
        bookHateoasAssembler.addLinks(bookDTOS, includeRelatedLinks);
        return bookDTOS;
    }

    @GetMapping("/get-all-by-genre/{genreId}")
    public Page<BookDTO> getAllByGenre(@PathVariable("genreId") int genreId,
                                       @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                       @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                       @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                       @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                       @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Genre genre = genreService.getById(genreId);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);

        Page<BookDTO> bookDTOS = bookService.getAllByGenre(genre, pageable).map(bookMapper::toDTO);
        bookHateoasAssembler.addLinks(bookDTOS, includeRelatedLinks);
        return bookDTOS;
    }


    @GetMapping("/search")
    public Page<BookDTO> getAllByTitleFirstLetter(@RequestParam("firstLetter") char firstLetter,
                                                  @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                                  @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                                  @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                                  @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                                  @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);

        Page<BookDTO> bookDTOS = bookService.getAllByTitleFirstLetter(firstLetter, pageable).map(bookMapper::toDTO);
        bookHateoasAssembler.addLinks(bookDTOS, includeRelatedLinks);
        return bookDTOS;
    }
}