package com.elleined.libraryapi.service.book;

import com.elleined.libraryapi.exception.resource.ResourceNotFoundException;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Set;

public interface BookService {
    Book getById(int id) throws ResourceNotFoundException;
    Book getByIsbn(String isbn) throws ResourceNotFoundException;
    Book save(String title,
              String isbn,
              String description,
              LocalDate publishedDate,
              int pages,
              Author author,
              Set<Genre> genres);

    Book update(Book book,
                String title,
                String description,
                int pages,
                Set<Genre> genres);

    boolean isbnAlreadyExists(String isbn);

    Page<Book> getAll(Author author, Pageable pageable);
    Page<Book> getAllByGenre(Genre genre, Pageable pageable);
    Page<Book> getAllByTitleFirstLetter(char firstLetter, Pageable pageable);
}
