package com.elleined.libraryapi.service.genre;

import com.elleined.libraryapi.dto.GenreDTO;
import com.elleined.libraryapi.exception.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.NotFoundException;
import com.elleined.libraryapi.mapper.GenreMapper;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.genre.Genre;
import com.elleined.libraryapi.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<Book> getAllByGenre(String genreName) {
        return null;
    }

    @Override
    public GenreDTO getById(int id) throws NotFoundException {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre does not exists"));
        return this.genreMapper.toDTO(genre);
    }

    @Override
    public GenreDTO getByName(String name) throws NotFoundException {
        Genre genre = genreRepository.fetchByName(name).orElseThrow(() -> new NotFoundException("Genre with name of " + name + " does not exists"));
        return this.genreMapper.toDTO(genre);
    }

    @Override
    public List<GenreDTO> getAllById(List<Integer> ids) {
        return ids.stream()
                .map(this::getById)
                .toList();
    }

    @Override
    public List<String> searchByFirstLetter(char firstLetter) {
        return genreRepository.searchByFirstLetter(firstLetter);
    }

    @Override
    public List<GenreDTO> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @Override
    public List<GenreDTO> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);

        return genreRepository.findAll(pageable)
                .stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @Override
    public List<GenreDTO> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, sortDirection, sortProperty);

        return genreRepository.findAll(pageable)
                .stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @Override
    public List<GenreDTO> saveAll(Collection<GenreDTO> genres) {
        return genres.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public GenreDTO save(GenreDTO genreDTO) throws FieldAlreadyExistsException, IllegalArgumentException {
        if (StringValidator.validate(genreDTO.getName())) throw new IllegalArgumentException("Genre name cannot be null or empty");
        if (isNameAlreadyExists(genreDTO.getName())) throw new FieldAlreadyExistsException("Genre with name of " + genreDTO.getName() + " already exists");

        Genre genre = genreMapper.toEntity(genreDTO);
        Genre savedGenre = genreRepository.save(genre);
        log.debug("Genre saved successfully {}", genreDTO.getName());
        return genreMapper.toDTO( savedGenre );
    }

    @Override
    public void update(int id, String newGenreName) throws NotFoundException, FieldAlreadyExistsException, IllegalArgumentException {
        if (StringValidator.validate(newGenreName)) throw new IllegalArgumentException("Genre name cannot be null or empty");
        if (isNameAlreadyExists(newGenreName)) throw new FieldAlreadyExistsException("Genre with name of " + newGenreName + " already exists");
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException("Genre with id of " + id + " of does not exists"));

        genre.setName(newGenreName);

        genreRepository.save(genre);
        log.debug("Genre with id of {} updated successfully", id);
    }

    @Override
    public boolean isNameAlreadyExists(String genreName) {
        return genreRepository.findAll()
                .stream()
                .map(Genre::getName)
                .anyMatch(genreName::equalsIgnoreCase);
    }
}
