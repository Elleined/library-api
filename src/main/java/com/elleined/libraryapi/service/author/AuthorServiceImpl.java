package com.elleined.libraryapi.service.author;

import com.elleined.libraryapi.exception.resource.ResourceNotFoundException;
import com.elleined.libraryapi.mapper.AuthorMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public Author getById(int id) throws ResourceNotFoundException {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with id of " + id + " does not exists"));
    }

    @Override
    public Author save(String name, String biography) {
        Author author = authorMapper.toEntity(name, biography);

        authorRepository.save(author);
        log.debug("Saving author success");
        return author;
    }

    @Override
    public Author update(Author author, String name, String biography) {
        author.setName(name);
        author.setBiography(biography);

        authorRepository.save(author);
        log.debug("Updating author success");
        return author;
    }

    @Override
    public Page<Author> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Page<Author> getAllByNameFirstLetter(char firstLetter, Pageable pageable) {
        return authorRepository.findAllByNameFirstLetter(String.valueOf(firstLetter), pageable);
    }
}
