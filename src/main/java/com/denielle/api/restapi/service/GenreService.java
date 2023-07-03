package com.denielle.api.restapi.service;

import com.denielle.api.restapi.dto.GenreDTO;
import com.denielle.api.restapi.exception.FieldAlreadyExistsException;
import com.denielle.api.restapi.exception.NotFoundException;
import com.denielle.api.restapi.mapper.GenreMapper;
import com.denielle.api.restapi.model.Genre;
import com.denielle.api.restapi.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreDTO getById(int id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre does not exists"));
        return this.genreMapper.toDTO(genre);
    }

    public GenreDTO getByName(String name) {
        Genre genre = genreRepository.fetchByName(name).orElseThrow(() -> new NotFoundException("Genre with name of " + name + " does not exists"));
        return this.genreMapper.toDTO(genre);
    }

    public List<GenreDTO> getAllById(List<Integer> ids) {
        return ids.stream()
                .map(this::getById)
                .toList();
    }

    public List<String> searchByFirstLetter(char firstLetter) {
        return genreRepository.searchByFirstLetter(firstLetter);
    }

    public List<GenreDTO> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    public List<GenreDTO> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);

        return genreRepository.findAll(pageable)
                .stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    public List<GenreDTO> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, sortDirection, sortProperty);

        return genreRepository.findAll(pageable)
                .stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    public List<Integer> saveAll(List<String> genres) {
        return genres.stream()
                .map(this::save)
                .toList();
    }

    public int save(String genreName) throws FieldAlreadyExistsException {
        if (isNameAlreadyExists(genreName)) throw new FieldAlreadyExistsException("Genre with name of " + genreName + " already exists");
        Genre genre = Genre.builder()
                .name(genreName)
                .createdAt(LocalDateTime.now())
                .build();

        genreRepository.save(genre);
        log.debug("Genre saved successfully {}", genreName);
        return genre.getId();
    }

    public void delete(int id) {
        genreRepository.deleteById(id);
        log.debug("Genre with id of {} deleted successfully", id);
    }

    public void deleteAllById(Set<Integer> ids) {
        genreRepository.deleteAllById(ids);
        log.debug("Genre with id of {} deleted successfully", ids);
    }

    public void update(int id, String newGenreName) {
        if (isNameAlreadyExists(newGenreName)) throw new FieldAlreadyExistsException("Genre name already exists");
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre with id of " + id + " of does not exists"));

        genre.setName(newGenreName);
        genre.setUpdatedAt(LocalDateTime.now());

        genreRepository.save(genre);
        log.debug("Genre with id of {} updated successfully", id);
    }

    public boolean isNameAlreadyExists(String genreName) {
        return genreRepository.findAll()
                .stream()
                .map(Genre::getName)
                .anyMatch(genreName::equalsIgnoreCase);
    }
}
