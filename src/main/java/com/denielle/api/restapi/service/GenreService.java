package com.denielle.api.restapi.service;

import com.denielle.api.restapi.dto.GenreDTO;
import com.denielle.api.restapi.exception.NameAlreadyExistsException;
import com.denielle.api.restapi.exception.NotFoundException;
import com.denielle.api.restapi.model.Genre;
import com.denielle.api.restapi.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<GenreDTO> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public GenreDTO getById(int genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new NotFoundException("Genre does not exists"));
        return this.convertToDTO(genre);
    }

    public List<GenreDTO> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);

        return genreRepository.findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<GenreDTO> getAll(int pageNumber, int pageSize, Sort.Direction direction, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, direction, sortProperty);

        return genreRepository.findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public int save(String genreName) throws NameAlreadyExistsException {
        if (isNameAlreadyExists(genreName)) throw new NameAlreadyExistsException("Genre name already exists");
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

    @Transactional
    public void delete(int genreId) {
        genreRepository.deleteById(genreId);
        log.debug("Genre with id of {} deleted successfully", genreId);
    }

    @Transactional
    public void update(int genreId, String newGenreName) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new NotFoundException("Genre does not exists"));

        genre.setName(newGenreName);
        genreRepository.save(genre);
        log.debug("Genre with id of {} updated successfully", genreId);
    }

    public GenreDTO convertToDTO(Genre genre) {
        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
