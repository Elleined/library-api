package com.elleined.libraryapi;

import com.elleined.libraryapi.populator.Populator;
import com.elleined.libraryapi.repository.GenreRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AfterStartUp {

    private final Populator genrePopulator;
    private final Populator authorPopulator;
    private final Populator bookPopulator;
    private final GenreRepository genreRepository;

    private final static String GENRES_JSON = "/json/genres.json";
    private final static String AUTHORS_JSON = "json/authors.json";
    private final static String BOOKS_JSON = "/json/books.json";

    public AfterStartUp(@Qualifier("genrePopulator") Populator genrePopulator,
                        @Qualifier("authorPopulator") Populator authorPopulator,
                        @Qualifier("bookPopulator") Populator bookPopulator, GenreRepository genreRepository) {
        this.genrePopulator = genrePopulator;
        this.authorPopulator = authorPopulator;
        this.bookPopulator = bookPopulator;
        this.genreRepository = genreRepository;
    }

    @PostConstruct
    public void init() throws IOException {
        if (genreRepository.existsById(1)) {
            log.debug("Returing because initial values for genres, authors, and books are already been saved!");
            return;
        }

        log.debug("Saving initial values for genres, authors, and books! Please wait...");
        genrePopulator.populate(GENRES_JSON);
        authorPopulator.populate(AUTHORS_JSON);
        bookPopulator.populate(BOOKS_JSON);
        log.debug("Saving initial values for genres, authors, and books success! Thanksss... :) ");
    }
}