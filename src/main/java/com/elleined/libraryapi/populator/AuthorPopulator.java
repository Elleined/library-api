package com.elleined.libraryapi.populator;

import com.elleined.libraryapi.dto.AuthorDTO;
import com.elleined.libraryapi.service.author.AuthorService;
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
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(Set.class, AuthorDTO.class);

        Set<AuthorDTO> authors = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        authorService.saveAll(authors);
    }
}
