package com.denielle.api.restapi.service;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.exception.NameAlreadyExistsException;
import com.denielle.api.restapi.exception.NotFoundException;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.model.Book;
import com.denielle.api.restapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

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

    public AuthorDTO getById(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));
        return this.convertToDTO(author);
    }

    public AuthorDTO getByName(String name) {
        Author author = authorRepository.fetchByName(name).orElseThrow(() -> new NameAlreadyExistsException("Author with name of " + name + " already exists"));
        return this.convertToDTO(author);
    }

    public AuthorDTO convertToDTO(Author author) {
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .biography(author.getBiography())
                .bookList(author.getBookList()
                        .stream()
                        .map(Book::getTitle)
                        .toList())
                .build();
    }
}
