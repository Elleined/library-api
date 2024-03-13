package com.elleined.libraryapi.service.genre;

import com.elleined.libraryapi.exception.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.NotFoundException;
import com.elleined.libraryapi.mapper.GenreMapper;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.repository.GenreRepository;
import com.elleined.libraryapi.service.PageSorter;
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
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public Set<Book> getAllByGenre(Genre genre) {
        return genre.getBooks();
    }

    @Override
    public Genre getById(int id) throws NotFoundException {
        return genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre does not exists"));
    }

    @Override
    public Set<Genre> getAllById(Set<Integer> ids) {
        return genreRepository.findAllById(ids);
    }

    @Override
    public List<Genre> searchByFirstLetter(char firstLetter) {
        return genreRepository.searchByFirstLetter(firstLetter);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);
        return genreRepository.findAll(pageable).toList();
    }

    @Override
    public List<Genre> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, sortDirection, sortProperty);
        return genreRepository.findAll(pageable).toList();
    }

    @Override
    public Genre save(String name) {
        if (isNameAlreadyExists(name)) throw new FieldAlreadyExistsException("Genre with name of " + name + " already exists");
        Genre genre = genreMapper.toEntity(name);
        genreRepository.save(genre);
        log.debug("Genre saved successfully with id of {}", genre.getId());
        return genre;
    }

    @Override
    public void update(Genre genre, String newGenreName) throws NotFoundException, FieldAlreadyExistsException, IllegalArgumentException {
        if (isNameAlreadyExists(newGenreName)) throw new FieldAlreadyExistsException("Genre with name of " + newGenreName + " already exists");
        genre.setName(newGenreName);
        genreRepository.save(genre);
        log.debug("Genre with id of {} updated successfully", genre.getId());
    }

    @Override
    public boolean isNameAlreadyExists(String genreName) {
        return genreRepository.findAll().stream()
                .map(Genre::getName)
                .anyMatch(genreName::equalsIgnoreCase);
    }
}
