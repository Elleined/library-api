package com.elleined.libraryapi.populator;

import com.elleined.libraryapi.mapper.BookMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.repository.AuthorRepository;
import com.elleined.libraryapi.repository.BookRepository;
import com.elleined.libraryapi.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Transactional
@RequiredArgsConstructor
public class BookPopulator implements Populator {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    private final Faker faker;

    @Override
    public void populate() throws IOException {
        Author author1 = authorRepository.findById(1).orElseThrow();

        Genre genre1 = genreRepository.findById(1).orElseThrow();
        Genre genre2 = genreRepository.findById(2).orElseThrow();
        Set<Genre> genres = Set.of(genre1, genre2);

        bookRepository.saveAll(
                this.getUniqueISBNs().stream()
                        .map(isbn -> bookMapper.toEntity(faker.book().title(), isbn, faker.lorem().sentence(), faker.date().birthday().toLocalDateTime().toLocalDate(), faker.number().randomDigit(), author1, genres))
                        .toList()
        );
    }

    private List<String> getUniqueISBNs() {
        List<String> isbns = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String isbn = faker.code().isbn10();
            if (isbns.contains(isbn))
                return getUniqueISBNs();

            isbns.add(isbn);
        }

        return isbns;
    }
}
