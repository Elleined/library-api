package com.elleined.libraryapi.service.book;

import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.exception.field.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.resource.ResourceNotFoundException;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookService {
    Book getById(int id) throws ResourceNotFoundException;

    Book getByIsbn(String isbn) throws ResourceNotFoundException;

    List<Book> getAllById(List<Integer> bookIds);
    List<Book> getAllByTitleFirstLetter(char firstLetter);

    List<Book> getAll();

    List<Book> getAll(int pageNumber, int pageSize);

    List<Book> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty);

    Set<Book> saveAll(Set<BookDTO> bookDTOS);

    Book save(String title,
              String isbn,
              String description,
              LocalDate publishedDate,
              int pages,
              Author author,
              Set<Genre> genres) throws FieldAlreadyExistsException, ResourceNotFoundException;

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
