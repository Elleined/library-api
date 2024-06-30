package com.elleined.libraryapi.service.book;

import com.elleined.libraryapi.exception.resource.ResourceAlreadyExistsException;
import com.elleined.libraryapi.exception.resource.ResourceNotFoundException;
import com.elleined.libraryapi.mapper.BookMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Book getById(int id) throws ResourceNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with id of " + id + " does not exists"));
    }

    @Override
    public Book getByIsbn(String isbn) throws ResourceNotFoundException {
        return bookRepository.findByISBN(isbn).orElseThrow(() -> new ResourceNotFoundException("Book with isbn of " + isbn + " does not exists"));
    }

    @Override
    public Book save(String title, String isbn, String description, LocalDate publishedDate, int pages, Author author, Set<Genre> genres) {
        if (isbnAlreadyExists(isbn))
            throw new ResourceAlreadyExistsException("Cannot save book! because isbn of " + isbn + " already exists!");

        Book book = bookMapper.toEntity(title, isbn, description, publishedDate, pages, author, genres);

        bookRepository.save(book);
        log.debug("Saving book success");
        return book;
    }

    @Override
    public Book update(Book book, String title, String description, int pages, Set<Genre> genres) {
        book.setTitle(title);
        book.setDescription(description);
        book.setPages(pages);
        book.setGenres(genres);

        bookRepository.save(book);
        log.debug("Updating book success");
        return book;
    }

    @Override
    public boolean isbnAlreadyExists(String isbn) {
        return bookRepository.findAll().stream()
                .map(Book::getIsbn)
                .anyMatch(isbn::equalsIgnoreCase);
    }

    @Override
    public Page<Book> getAll(Author author, Pageable pageable) {
        return bookRepository.findAll(author, pageable);
    }

    @Override
    public Page<Book> getAllByGenre(Genre genre, Pageable pageable) {
        return bookRepository.findAllByGenre(genre, pageable);
    }

    @Override
    public Page<Book> getAllByTitleFirstLetter(char firstLetter, Pageable pageable) {
        return bookRepository.findAllByTitleFirstLetter(firstLetter, pageable);
    }
}
