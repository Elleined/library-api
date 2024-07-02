package com.elleined.libraryapi.service.author;

import com.elleined.libraryapi.exception.resource.ResourceNotFoundException;
import com.elleined.libraryapi.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Author getById(int id) throws ResourceNotFoundException;
    Author save(String name, String biography);
    Author update(Author author, String name, String biography);

    Page<Author> getAll(Pageable pageable);
    Page<Author> getAllByNameFirstLetter(char firstLetter, Pageable pageable);
}
