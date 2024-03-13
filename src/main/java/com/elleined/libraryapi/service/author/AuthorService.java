package com.elleined.libraryapi.service.author;

import com.elleined.libraryapi.dto.AuthorDTO;
import com.elleined.libraryapi.exception.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.NotFoundException;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;

import java.util.List;

public interface AuthorService {
    Author getById(int id) throws NotFoundException;

    List<Book> getAllBooks(Author author) throws NotFoundException;

    List<Author> searchByFirstLetter(char firstLetter);

    List<Author> getAll();

    List<Author> getAllById(List<Integer> ids);

    List<Author> getAll(int pageNumber, int pageSize);

    List<Author> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty);

    List<Author> saveAll(List<Author> authors);

    Author save(AuthorDTO authorDTO) throws FieldAlreadyExistsException;

    void update(int id, AuthorDTO authorDTO) throws NotFoundException, FieldAlreadyExistsException;

    boolean isNameAlreadyExists(String authorName);
}
