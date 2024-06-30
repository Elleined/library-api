package com.elleined.libraryapi.populator;

import com.elleined.libraryapi.mapper.GenreMapper;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class GenrePopulator implements Populator {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    private final Faker faker;

    @Override
    public void populate() throws IOException {
        List<Genre> genres = List.of(
                genreMapper.toEntity(faker.book().genre()),
                genreMapper.toEntity(faker.book().genre()),
                genreMapper.toEntity(faker.book().genre()),
                genreMapper.toEntity(faker.book().genre()),
                genreMapper.toEntity(faker.book().genre())
        );

        genreRepository.saveAll(genres);
    }
}
