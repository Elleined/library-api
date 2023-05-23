package com.denielle.api.restapi.service;

import com.denielle.api.restapi.dto.GenreDTO;
import com.denielle.api.restapi.exception.genre.GenreNameAlreadyExistsException;
import com.denielle.api.restapi.exception.genre.GenreNotFoundException;
import com.denielle.api.restapi.model.Genre;
import com.denielle.api.restapi.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    @Transactional
    public int save(String genreName) throws GenreNameAlreadyExistsException {
        if (isNameAlreadyExists(genreName)) throw new GenreNameAlreadyExistsException("Genre name already exists");
        Genre genre = Genre.builder()
                .name(genreName)
                .build();

        genreRepository.save(genre);
        log.debug("Genre saved successfully {}", genreName);
        return genre.getId();
    }

    public boolean isNameAlreadyExists(String genreName) {
        return genreRepository.findAll()
                .stream()
                .map(Genre::getName)
                .anyMatch(genreName::equalsIgnoreCase);
    }

    public List<GenreDTO> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public GenreDTO getById(int genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new GenreNotFoundException("Genre does not exists"));
        return this.convertToDTO(genre);
    }

    public void delete(int genreId) {
        genreRepository.deleteById(genreId);
    }

    public void update(int genreId, String newGenreName) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new GenreNotFoundException("Genre does not exists"));

        genre.setName(newGenreName);
        genreRepository.save(genre);
    }

    public GenreDTO convertToDTO(Genre genre) {
        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
