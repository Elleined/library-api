package com.elleined.libraryapi.service.book;

import com.elleined.libraryapi.exception.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.NotFoundException;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookService {
    Book getById(int id) throws NotFoundException;

    Book getByIsbn(String isbn) throws NotFoundException;

    List<Book> getAllById(List<Integer> bookIds);
    List<Book> getAllByTitleFirstLetter(char firstLetter);

    List<Book> getAll();

    List<Book> getAll(int pageNumber, int pageSize);

    List<Book> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty);

    List<Book> saveAll(List<Book> books);

    Book save(String title,
              String isbn,
              String description,
              LocalDate publishedDate,
              int pages,
              Author author,
              Set<Genre> genres) throws FieldAlreadyExistsException, NotFoundException;

    void update(Book book,
                String title,
                String isbn,
                String description,
                LocalDate publishedDate,
                int pages,
                Author author,
                Set<Genre> genres) throws FieldAlreadyExistsException, IllegalArgumentException;

    boolean isbnAlreadyExists(String isbn);
}
