package com.denielle.api.restapi.service;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.exception.NameAlreadyExistsException;
import com.denielle.api.restapi.exception.NotFoundException;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.model.Book;
import com.denielle.api.restapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final DTOConverter<Book, BookDTO> converter;

    public AuthorDTO getById(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));
        return this.convertToDTO(author);
    }

    public AuthorDTO getByName(String name) {
        Author author = authorRepository.fetchByName(name).orElseThrow(() -> new NameAlreadyExistsException("Author with name of " + name + " does not exists"));
        return this.convertToDTO(author);
    }

    public List<String> getAllBooks(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));
        return author.getBookList()
                .stream()
                .map(Book::getTitle)
                .toList();
    }

    public int getBookCount(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));
        return author.getBookList().size();
    }

    public List<String> searchByFirstLetter(char firstLetter) {
        return authorRepository.searchByFirstLetter(firstLetter);
    }

    public List<AuthorDTO> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<AuthorDTO> getAllById(List<Integer> ids) {
        return ids.stream()
                .map(this::getById)
                .toList();
    }

    public List<AuthorDTO> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);

        return authorRepository.findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<AuthorDTO> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, sortDirection, sortProperty);

        return authorRepository.findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<Integer> saveAll(List<AuthorDTO> authors) {
        return authors.stream()
                .map(this::save)
                .toList();
    }

    public int save(AuthorDTO authorDTO) throws NameAlreadyExistsException {
        if (isNameAlreadyExists(authorDTO.getName())) throw new NameAlreadyExistsException("Author with name of " + authorDTO.getName() + " already exists");
        Author author = Author.builder()
                .name(authorDTO.getName())
                .biography(authorDTO.getBiography())
                .createdAt(LocalDateTime.now())
                .build();

        authorRepository.save(author);
        log.debug("Author saved successfully {}", author.getName());
        return author.getId();
    }

    public void delete(int id) {
        authorRepository.deleteById(id);
        log.debug("Author with id of {} deleted successfully", id);
    }

    public void update(int id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));

        author.setName(authorDTO.getName());
        author.setBiography(authorDTO.getBiography());
        author.setUpdatedAt(LocalDateTime.now());

        authorRepository.save(author);

        log.debug("Author with id of {} updated successfully", id);
    }

    public boolean isNameAlreadyExists(String authorName) {
        return authorRepository.findAll()
                .stream()
                .map(Author::getName)
                .anyMatch(authorName::equalsIgnoreCase);
    }

    public AuthorDTO convertToDTO(Author author) {
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .biography(author.getBiography())
                .createdAt(author.getCreatedAt())
                .updatedAt(author.getUpdatedAt())
                .books(author.getBookList()
                        .stream()
                        .map(converter::convertToDTO)
                        .toList())
                .bookCount(author.getBookList().size())
                .build();
    }
}
