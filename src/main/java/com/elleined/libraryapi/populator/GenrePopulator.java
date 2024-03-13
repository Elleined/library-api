package com.elleined.libraryapi.populator;


import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.dto.GenreDTO;
import com.elleined.libraryapi.service.genre.GenreService;
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
@Qualifier("genrePopulator")
@Transactional
public class GenrePopulator extends Populator {

    private final GenreService genreService;

    public GenrePopulator(ObjectMapper objectMapper, GenreService genreService) {
        super(objectMapper);
        this.genreService = genreService;
    }

    @Override
    public void populate(String jsonFile) throws IOException {
        var resource = new ClassPathResource(jsonFile);
        byte[] dataBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        var type = objectMapper.getTypeFactory().constructCollectionType(Set.class, GenreDTO.class);

        Set<GenreDTO> genreDTOS = objectMapper.readValue(new String(dataBytes, StandardCharsets.UTF_8), type);
        genreService.saveAll(genreDTOS);
    }
}
