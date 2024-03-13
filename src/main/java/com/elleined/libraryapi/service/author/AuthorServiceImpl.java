package com.elleined.libraryapi.service.author;

import com.elleined.libraryapi.dto.AuthorDTO;
import com.elleined.libraryapi.exception.field.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.field.RequiredFieldException;
import com.elleined.libraryapi.exception.resource.ResourceNotFoundException;
import com.elleined.libraryapi.mapper.AuthorMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.repository.AuthorRepository;
import com.elleined.libraryapi.service.PageSorter;
import com.elleined.libraryapi.service.StringValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public Author getById(int id) {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id of " + id + " does not exists"));
    }

    @Override
    public List<Book> getAllBooks(Author author) {
        return author.getBooks();
    }

    @Override
    public List<Author> searchByFirstLetter(char firstLetter) {
        return authorRepository.searchByFirstLetter(firstLetter);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> getAllById(List<Integer> ids) {
        return authorRepository.findAllById(ids);
    }

    @Override
    public List<Author> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);
        return authorRepository.findAll(pageable).toList();
    }

    @Override
    public List<Author> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, sortDirection, sortProperty);
        return authorRepository.findAll(pageable).stream().toList();
    }

    @Override
    public Author save(String name, String biography) {
        if (isNameAlreadyExists(name)) throw new FieldAlreadyExistsException("Author with name of " + name + " already exists");
        if (StringValidator.validate(name)) throw new RequiredFieldException("Name is required!");
        if (StringValidator.validate(biography)) throw new RequiredFieldException("Biography is required!");

        Author author = authorMapper.toEntity(name, biography);
        authorRepository.save(author);
        log.debug("Author with id of {} saved successfully!", author.getId());
        return author;
    }

    @Override
    public Set<Author> saveAll(Set<AuthorDTO> authorDTOS) {
        if (authorDTOS.stream().map(AuthorDTO::getName).anyMatch(this::isNameAlreadyExists)) throw new FieldAlreadyExistsException("Cannot save pre-defined authors! Because one of the author has duplicate name!");

        Set<Author> authors = authorDTOS.stream()
                .map(authorDTO -> authorMapper.toEntity(authorDTO.getName(), authorDTO.getBiography()))
                .collect(Collectors.toSet());

        authorRepository.saveAll(authors);
        log.debug("Saving pre-defined authors success...");
        return authors;
    }

    @Override
    public void update(Author author, String name, String biography) {
        author.setName(name);
        author.setBiography(biography);
        author.setUpdatedAt(LocalDateTime.now());
        authorRepository.save(author);
        log.debug("Author with id of {} updated successfully", author.getId());
    }

    @Override
    public boolean isNameAlreadyExists(String authorName) {
        return authorRepository.findAll().stream()
                .map(Author::getName)
                .anyMatch(authorName::equalsIgnoreCase);
    }
}
