package com.elleined.libraryapi.service.genre;

import com.elleined.libraryapi.exception.resource.ResourceAlreadyExistsException;
import com.elleined.libraryapi.exception.resource.ResourceNotFoundException;
import com.elleined.libraryapi.mapper.GenreMapper;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public Genre getById(int id) throws ResourceNotFoundException {
        return genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre does not exists"));
    }

    @Override
    public Page<Genre> getAll(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @Override
    public Genre save(String genreName) {
        if (isNameAlreadyExists(genreName))
            throw new ResourceAlreadyExistsException("Cannot save genre! because genre with name of " + genreName + " already exists");

        Genre genre = genreMapper.toEntity(genreName);

        genreRepository.save(genre);
        log.debug("Saving genre success");
        return genre;
    }

    @Override
    public Genre update(Genre genre, String newGenreName) {
        if (isNameAlreadyExists(newGenreName))
            throw new ResourceAlreadyExistsException("Cannot update genre! because genre with name of " + newGenreName + " already exists");

        genre.setName(newGenreName);

        genreRepository.save(genre);
        log.debug("Updating genre success");
        return genre;
    }

    @Override
    public boolean isNameAlreadyExists(String genreName) {
        return genreRepository.findAll().stream()
                .map(Genre::getName)
                .anyMatch(genreName::equalsIgnoreCase);
    }

    @Override
    public Page<Genre> getAllByFirstLetter(char firstLetter, Pageable pageable) {
        return genreRepository.findAllByFirstLetter(firstLetter, pageable);
    }

    @Override
    public Set<Genre> getAllById(Set<Integer> ids) {
        return new HashSet<>(genreRepository.findAllById(ids));
    }
}
