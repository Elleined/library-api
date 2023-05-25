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
    private static final LocalDateTime now = LocalDateTime.now();

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
        log.debug("Initial genres record saved");
        authorService.saveAll(this.authors());
        log.debug("Initial authors record saved");
    }

    private List<Genre> genres() {
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

    private List<Author> authors() {
        return List.of(
                new Author("Bret Easton Ellis", "Bret Easton Ellis is an American author, screenwriter, short-story writer, and director. Ellis was first regarded as one of the so-called literary Brat Pack and is a self-proclaimed satirist whose trademark technique, as a writer, is the expression of extreme acts and opinions in an affectless style.", now),
                new Author("Stephen Edwin King", "Stephen Edwin King is an American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels. Described as the 'King of Horror', his books have sold more than 350 million copies as of 2006, and many have been adapted into films, television series, miniseries, and comic books.", now),
                new Author("Mario Francis Puzo", "Mario Francis Puzo was an American author, screenwriter, and journalist. He is known for his crime novels about the Italian-American Mafia and Sicilian Mafia, most notably The Godfather, which he later co-adapted into a film trilogy directed by Francis Ford Coppola.", now),
                new Author("William Thomas Harris III", "William Thomas Harris III is an American writer, best known for a series of suspense novels about his most famous character, Hannibal Lecter.", now),
                new Author("Nelle Harper Lee", "Nelle Harper Lee was an American novelist. She wrote the 1960 novel To Kill a Mockingbird that won the 1961 Pulitzer Prize and became a classic of modern American literature.", now),
                new Author("John Ronald Reuel Tolkien", "John Ronald Reuel Tolkien CBE FRSL was an English writer and philologist. He was the author of the high fantasy works The Hobbit and The Lord of the Rings. From 1925 to 1945, Tolkien was the Rawlinson and Bosworth Professor of Anglo-Saxon and a Fellow of Pembroke College, both at the University of Oxford. ", now),
                new Author("J.K Rowling", "Joanne Rowling CH OBE FRSL, best known by her pen name J. K. Rowling, is a British author and philanthropist. She wrote Harry Potter, a seven-volume children's fantasy series published from 1997 to 2007.", now),
                new Author("L. Frank Baum", "Lyman Frank Baum was an American author best known for his children's books, particularly The Wonderful Wizard of Oz, part of a series. In addition to the 14 Oz books, Baum penned 41 other novels, 83 short stories, over 200 poems, and at least 42 scripts.", now),
                new Author("Margaret Mitchell", "Margaret Munnerlyn Mitchell was an American novelist and journalist. Mitchell wrote only one novel, published during her lifetime, the American Civil War-era novel Gone with the Wind, for which she won the National Book Award for Fiction for Most Distinguished Novel of 1936 and the Pulitzer Prize for Fiction in 1937.", now),
                new Author("William Goldman", "William Goldman was an American novelist, playwright, and screenwriter. He first came to prominence in the 1950s as a novelist before turning to screenwriting. He won Academy Awards for his screenplays Butch Cassidy and the Sundance Kid and All the President's Men.", now)
        );
    }
}
