package com.denielle.api.restapi.repository;

import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.model.Book;
import com.denielle.api.restapi.model.Genre;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED) // uncomment this to actually hit the database
class BookRepositoryTest {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookRepositoryTest(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Test
    public void save() {
        Author author = authorRepository.fetchByName("Sample author name").orElseThrow();

        Genre genre1 = genreRepository.fetchByName("Sample book genre1").orElseThrow();
        Genre genre2 = genreRepository.fetchByName("Sample book genre2").orElseThrow();
        Set<Genre> genres = new HashSet<>(List.of(genre1, genre2));

        Book book = Book.builder()
                .title("Sample book title")
                .description("Sample book description")
                .isbn("Sample isbn 9780307278692")
                .publishedDate(LocalDate.of(2001, Month.JUNE, 1))
                .pages(999)
                .author(author)
                .genres(genres)
                .build();

        bookRepository.save(book);
        log.debug("Book successfully saved {}", book.getTitle());
    }

    @Test
    void searchByFirstLetter() {
        char firstLetter = 'M';
        bookRepository.searchByFirstLetter(firstLetter).forEach(System.out::println);
    }

    @Test
    void getAllByGenre() {
        String genreName = "General Fiction";
        bookRepository.getAllByGenre(genreName)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }
}