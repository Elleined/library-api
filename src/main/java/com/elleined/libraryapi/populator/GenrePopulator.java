package com.elleined.libraryapi.populator;

import com.elleined.libraryapi.mapper.GenreMapper;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
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
        genreRepository.saveAll(
                this.getUniqueGenres().stream()
                .map(genreMapper::toEntity)
                .toList()
        );
    }

    private List<String> getUniqueGenres() {
        List<String> genres = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String genre = faker.book().genre();
            if (genres.contains(genre)) {
                return getUniqueGenres();
            }
            genres.add(genre);
        }

        return genres;
    }
}
