package com.denielle.api.restapi;

import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.model.Genre;
import com.denielle.api.restapi.service.AuthorService;
import com.denielle.api.restapi.service.BookService;
import com.denielle.api.restapi.service.GenreService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AfterStartUp {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @PostConstruct
    public void init() {
        if (genreService.isNameAlreadyExists("Drama")) {
            log.debug("Returning Because Initial Database record are already saved!");
            return;
        }

        genreService.saveAll(this.genres());

    }

    private List<Genre> genres() {
        LocalDateTime now = LocalDateTime.now();
        return List.of(
                new Genre("Drama", now),
                new Genre("Comedy", now),
                new Genre("Action", now),
                new Genre("Horror", now),
                new Genre("Romance", now),
                new Genre("Thriller", now),
                new Genre("Fantasy", now),
                new Genre("Fiction", now),
                new Genre("Adventure", now),
                new Genre("Mystery", now),
                new Genre("Crime", now),
                new Genre("War", now),
                new Genre("Musical", now),
                new Genre("Sports", now),
                new Genre("Science Fiction", now),
                new Genre("Literary", now),
                new Genre("General Fiction", now)
        );
    }
}
