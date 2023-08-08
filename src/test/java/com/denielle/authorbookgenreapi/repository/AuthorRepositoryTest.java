package com.denielle.authorbookgenreapi.repository;

import com.denielle.authorbookgenreapi.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED) // uncomment this to actually hit the database
class AuthorRepositoryTest {
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorRepositoryTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    void save() {
        Author author = Author.builder()
                .name("Sample author name")
                .biography("Sample author biography")
                .build();

        authorRepository.save(author);
        log.debug("Author successfully saved {}",author.getName());
    }

    @Test
    void searchByFirstLetter() {
        char firstLetter = 'M';
        authorRepository.searchByFirstLetter(firstLetter).forEach(System.out::println);
    }

    @Test
    void test() {
        List<Author> authors = authorRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(author -> author.getBookList().size(),
                        Comparator.reverseOrder()))
                .toList();
        authors.forEach(author -> System.out.println(author.getName() + " book count: " + author.getBookList().size()));
    }

}