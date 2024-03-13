package com.elleined.libraryapi;

import com.elleined.libraryapi.populator.AuthorPopulator;
import com.elleined.libraryapi.populator.BookPopulator;
import com.elleined.libraryapi.populator.GenrePopulator;
import com.elleined.libraryapi.repository.GenreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class AfterStartUp {

    private final GenrePopulator genrePopulator;
    private final AuthorPopulator authorPopulator;
    private final BookPopulator bookPopulator;
    private final GenreRepository genreRepository;

    private final static String GENRES_JSON = "/json/genres.json";
    private final static String AUTHORS_JSON = "json/authors.json";
    private final static String BOOKS_JSON = "/json/books.json";


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