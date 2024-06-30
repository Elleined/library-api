package com.elleined.libraryapi.populator;

import com.elleined.libraryapi.mapper.AuthorMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class AuthorPopulator implements Populator {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    private final Faker faker;

    @Override
    public void populate() throws IOException {
        List<Author> authors = List.of(
                authorMapper.toEntity(faker.book().author(), faker.lorem().sentence()),
                authorMapper.toEntity(faker.book().author(), faker.lorem().sentence()),
                authorMapper.toEntity(faker.book().author(), faker.lorem().sentence()),
                authorMapper.toEntity(faker.book().author(), faker.lorem().sentence()),
                authorMapper.toEntity(faker.book().author(), faker.lorem().sentence())
        );

        authorRepository.saveAll(authors);
    }
}
