package com.elleined.libraryapi.service.genre;

import com.elleined.libraryapi.exception.resource.ResourceNotFoundException;
import com.elleined.libraryapi.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface GenreService {

    Genre getById(int id) throws ResourceNotFoundException;
    Page<Genre> getAll(Pageable pageable);
    Genre save(String genreName);
    Genre update(Genre genre, String newGenreName);
    boolean isNameAlreadyExists(String genreName);

    Page<Genre> getAllByNameFirstLetter(char firstLetter, Pageable pageable);

    // Application only methods
    Set<Genre> getAllById(Set<Integer> ids);
}
