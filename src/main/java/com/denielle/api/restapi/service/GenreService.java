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

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreDTO getById(int id) throws NotFoundException {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre does not exists"));
        return this.genreMapper.toDTO(genre);
    }

    public GenreDTO getByName(String name) throws NotFoundException {
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

    public List<Integer> saveAll(List<GenreDTO> genres) {
        return genres.stream()
                .map(this::save)
                .toList();
    }

    public int save(GenreDTO genreDTO) throws FieldAlreadyExistsException, IllegalArgumentException {
        if (StringValidator.validate(genreDTO.getName())) throw new IllegalArgumentException("Genre name cannot be null or empty");
        if (isNameAlreadyExists(genreDTO.getName())) throw new FieldAlreadyExistsException("Genre with name of " + genreDTO.getName() + " already exists");

        Genre genre = genreMapper.toEntity(genreDTO);
        genreRepository.save(genre);
        log.debug("Genre saved successfully {}", genreDTO.getName());
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

    public void update(int id, String newGenreName) throws NotFoundException, FieldAlreadyExistsException, IllegalArgumentException {
        if (StringValidator.validate(newGenreName)) throw new IllegalArgumentException("Genre name cannot be null or empty");
        if (isNameAlreadyExists(newGenreName)) throw new FieldAlreadyExistsException("Genre with name of " + newGenreName + " already exists");
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre with id of " + id + " of does not exists"));

        genre.setName(newGenreName);

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
