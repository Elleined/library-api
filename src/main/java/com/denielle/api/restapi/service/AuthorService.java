package com.denielle.api.restapi.service;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.exception.NameAlreadyExistsException;
import com.denielle.api.restapi.exception.NotFoundException;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.repository.AuthorRepository;
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
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorDTO> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<AuthorDTO> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);

        return authorRepository.findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<AuthorDTO> getAll(int pageNumber, int pageSize, Sort.Direction direction, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, direction, sortProperty);

        return authorRepository.findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    public AuthorDTO getById(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));
        return this.convertToDTO(author);
    }

    public AuthorDTO getByName(String name) {
        Author author = authorRepository.fetchByName(name).orElseThrow(() -> new NameAlreadyExistsException("Author with name of " + name + " already exists"));
        return this.convertToDTO(author);
    }

    @Transactional
    public int save(AuthorDTO authorDTO) {
        Author author = Author.builder()
                .name(authorDTO.getName())
                .biography(authorDTO.getBiography())
                .build();

        authorRepository.save(author);
        log.debug("Author saved successfully {}", author);
        return author.getId();
    }

    @Transactional
    public void delete(int id) {
        authorRepository.deleteById(id);
        log.debug("Author with id of {} deleted successfully", id);
    }

    @Transactional
    public void update(int id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));

        author.setName(authorDTO.getName());
        author.setBiography(authorDTO.getBiography());

        authorRepository.save(author);

        log.debug("Author with id of {} updated successfully", id);
    }


    public AuthorDTO convertToDTO(Author author) {
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .biography(author.getBiography())
                .build();
    }
}
