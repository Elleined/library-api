package com.elleined.bookauthorgenreapi.populator;

import com.elleined.bookauthorgenreapi.dto.BookDTO;
import com.elleined.bookauthorgenreapi.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
@Transactional
@Qualifier("bookPopulator")
public class BookPopulator extends Populator {

    private final BookService bookService;

    public BookPopulator(ObjectMapper objectMapper, BookService bookService) {
        super(objectMapper);
        this.bookService = bookService;
    }

    @Override
    public void populate(String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        var type = objectMapper.getTypeFactory().constructCollectionType(Set.class, BookDTO.class);

        Set<BookDTO> books = objectMapper.readValue(resource.getFile(), type);
        bookService.saveAll(books);
    }
}
