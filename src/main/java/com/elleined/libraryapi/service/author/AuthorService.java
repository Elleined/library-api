package com.elleined.libraryapi.service.author;

import com.elleined.libraryapi.dto.AuthorDTO;
import com.elleined.libraryapi.exception.field.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.resource.ResourceNotFoundException;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    Author getById(int id) throws ResourceNotFoundException;

    List<Book> getAllBooks(Author author) throws ResourceNotFoundException;

    List<Author> searchByFirstLetter(char firstLetter);

    List<Author> getAll();

    List<Author> getAllById(List<Integer> ids);

    List<Author> getAll(int pageNumber, int pageSize);

    List<Author> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty);

    Author save(String name, String biography) throws FieldAlreadyExistsException;

    Set<Author> saveAll(Set<AuthorDTO> authorDTOS);

    void update(Author author, String name, String biography) throws ResourceNotFoundException, FieldAlreadyExistsException;

    boolean isNameAlreadyExists(String authorName);
}
