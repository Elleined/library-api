package com.elleined.libraryapi.service.author;

import com.elleined.libraryapi.exception.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.NotFoundException;
import com.elleined.libraryapi.mapper.AuthorMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.repository.AuthorRepository;
import com.elleined.libraryapi.service.PageSorter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public Author getById(int id) throws NotFoundException {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundException("Author with id of " + id + " does not exists"));
    }

    @Override
    public List<Book> getAllBooks(Author author) throws NotFoundException {
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
    public List<Author> saveAll(List<Author> authors) {
        return authorRepository.saveAll(authors);
    }

    @Override
    public Author save(String name, String biography) throws FieldAlreadyExistsException {
        if (isNameAlreadyExists(name)) throw new FieldAlreadyExistsException("Author with name of " + name + " already exists");
        Author author = authorMapper.toEntity(name, biography);
        authorRepository.save(author);
        log.debug("Author with id of {} saved successfully!", author.getId());
        return author;
    }
    @Override
    public void update(Author author, String name, String biography) throws NotFoundException, FieldAlreadyExistsException {
        author.setName(name);
        author.setBiography(biography);
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
