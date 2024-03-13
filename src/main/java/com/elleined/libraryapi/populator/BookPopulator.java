package com.elleined.libraryapi.populator;

import com.elleined.libraryapi.dto.AuthorDTO;
import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.service.book.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(Set.class, BookDTO.class);

        Set<BookDTO> bookDTOS = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        bookService.saveAll(bookDTOS);
    }
}
