package com.elleined.bookauthorgenreapi.service;

import com.elleined.bookauthorgenreapi.dto.AuthorDTO;
import com.elleined.bookauthorgenreapi.exception.FieldAlreadyExistsException;
import com.elleined.bookauthorgenreapi.exception.NotFoundException;
import com.elleined.bookauthorgenreapi.mapper.AuthorMapper;
import com.elleined.bookauthorgenreapi.model.Author;
import com.elleined.bookauthorgenreapi.model.Book;
import com.elleined.bookauthorgenreapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorDTO getById(int id) throws NotFoundException {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));
        return authorMapper.toDTO(author);
    }

    public AuthorDTO getByName(String name) throws NotFoundException {
        Author author = authorRepository.fetchByName(name).orElseThrow(() -> new NotFoundException("Author with name of " + name + " does not exists"));
        return authorMapper.toDTO(author);
    }

    public List<String> getAllBooks(int id) throws NotFoundException {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));
        return author.getBookList()
                .stream()
                .map(Book::getTitle)
                .toList();
    }

    public int getBookCount(int id) throws NotFoundException {
        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));
        return author.getBookList().size();
    }

    public List<String> searchByFirstLetter(char firstLetter) {
        return authorRepository.searchByFirstLetter(firstLetter);
    }

    public List<AuthorDTO> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toDTO)
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
                .map(authorMapper::toDTO)
                .toList();
    }

    public List<AuthorDTO> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, sortDirection, sortProperty);

        return authorRepository.findAll(pageable)
                .stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    public List<AuthorDTO> saveAll(Collection<AuthorDTO> authors) {
        return authors.stream()
                .map(this::save)
                .toList();
    }

    public AuthorDTO save(AuthorDTO authorDTO) throws FieldAlreadyExistsException {
        if (isNameAlreadyExists(authorDTO.getName())) throw new FieldAlreadyExistsException("Author with name of " + authorDTO.getName() + " already exists");
        Author author = authorMapper.toEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        log.debug("Author with id of {} saved successfully!", savedAuthor.getId());
        return authorMapper.toDTO( savedAuthor );
    }

    public void update(int id, AuthorDTO authorDTO) throws NotFoundException, FieldAlreadyExistsException {
        if (isNameAlreadyExists(authorDTO.getName())) throw new FieldAlreadyExistsException("Author with name of " + authorDTO.getName() + " already exists");

        Author author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));

        authorMapper.updateEntity(author, authorDTO);
        authorRepository.save(author);

        log.debug("Author with id of {} updated successfully", id);
    }

    public boolean isNameAlreadyExists(String authorName) {
        return authorRepository.findAll()
                .stream()
                .map(Author::getName)
                .anyMatch(authorName::equalsIgnoreCase);
    }
}
