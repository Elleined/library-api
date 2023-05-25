package com.denielle.api.restapi.repository;

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

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED) // uncomment this to actually hit the database
class GenreRepositoryTest {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreRepositoryTest(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Test
    void save() {
        Genre genre1 = Genre.builder()
                .name("Sample book genre1")
                .build();

        Genre genre2 = Genre.builder()
                .name("Sample book genre2")
                .build();

        genreRepository.save(genre1);
        log.debug("Genre successfully saved {}", genre1.getName());
        genreRepository.save(genre2);
        log.debug("Genre successfully saved {}", genre2.getName());
    }

    @Test
    void searchByFirstLetter() {
        char firstLetter = 'M';
        genreRepository.searchByFirstLetter(firstLetter).forEach(System.out::println);
    }
}