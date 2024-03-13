package com.elleined.libraryapi.populator;

import com.elleined.libraryapi.dto.AuthorDTO;
import com.elleined.libraryapi.service.author.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
@Qualifier("authorPopulator")
@Transactional
public class AuthorPopulator extends Populator {
    private final AuthorService authorService;

    public AuthorPopulator(ObjectMapper objectMapper, AuthorService authorService) {
        super(objectMapper);
        this.authorService = authorService;
    }

    @Override
    public void populate(String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        var type = objectMapper.getTypeFactory().constructCollectionType(Set.class, AuthorDTO.class);

        Set<AuthorDTO> authors = objectMapper.readValue(resource.getFile(), type);
        authorService.saveAll(authors);
    }
}
