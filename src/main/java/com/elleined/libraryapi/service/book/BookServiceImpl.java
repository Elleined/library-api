package com.elleined.libraryapi.service.book;

import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.exception.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.NotFoundException;
import com.elleined.libraryapi.mapper.BookMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.repository.BookRepository;
import com.elleined.libraryapi.service.PageSorter;
import com.elleined.libraryapi.service.author.AuthorService;
import com.elleined.libraryapi.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public Book getById(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book with id of " + id + " does not exists"));
    }

    @Override
    public Book getByIsbn(String isbn) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Book with ISBN of " + isbn + " does not exists!"));
    }

    @Override
    public List<Book> getAllById(List<Integer> bookIds) {
        return bookRepository.findAllById(bookIds);
    }

    @Override
    public List<Book> getAllByTitleFirstLetter(char firstLetter) {
        return bookRepository.getAllByTitleFirstLetter(firstLetter);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);
        return bookRepository.findAll(pageable).toList();
    }

    @Override
    public List<Book> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, sortDirection, sortProperty);
        return bookRepository.findAll(pageable).toList();
    }

    @Override
    public Set<Book> saveAll(Set<BookDTO> bookDTOS) {
        Set<Book> books = bookDTOS.stream()
                .map(bookDTO -> {
                    Author author = authorService.getById(bookDTO.getAuthorId());
                    Set<Genre> genres = genreService.getAllById(bookDTO.getGenreIds());
                    return bookMapper.toEntity(bookDTO.getTitle(), bookDTO.getIsbn(), bookDTO.getDescription(), bookDTO.getPublishedDate(), bookDTO.getPages(), author, genres);
                })
                .collect(Collectors.toSet());

        bookRepository.saveAll(books);
        log.debug("Saving pre-defined books success...");
        return books;
    }

    @Override
    public Book save(String title, String isbn, String description, LocalDate publishedDate, int pages, Author author, Set<Genre> genres) {
        if (isbnAlreadyExists(isbn)) throw new FieldAlreadyExistsException("Book with isbn of " + isbn + " already exists!");

        Book book = bookMapper.toEntity(title, isbn, description, publishedDate, pages, author, genres);
        bookRepository.save(book);
        log.debug("Book saved successfully with id of {}", book.getId());
        return book;
    }

    @Override
    public void update(Book book,
                       String title,
                       String isbn,
                       String description,
                       LocalDate publishedDate,
                       int pages,
                       Author author,
                       Set<Genre> genres) {

        if (isbnAlreadyExists(isbn)) throw new FieldAlreadyExistsException("Book with isbn of " + isbn + " already exists!");

        book.setTitle(title);
        book.setIsbn(isbn);
        book.setDescription(description);
        book.setPublishedDate(publishedDate);
        book.setPages(pages);
        book.setAuthor(author);
        book.setGenres(genres);
        book.setUpdatedAt(LocalDateTime.now());

        bookRepository.save(book);
        log.debug("Book updated successfully");
    }

    @Override
    public boolean isbnAlreadyExists(String isbn) {
        return bookRepository.findAll().stream()
                .map(Book::getIsbn)
                .anyMatch(isbn::equalsIgnoreCase);
    }
}
