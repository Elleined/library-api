package com.elleined.libraryapi.populator;


import com.elleined.libraryapi.dto.GenreDTO;
import com.elleined.libraryapi.service.genre.GenreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
        var type = objectMapper.getTypeFactory().constructCollectionType(Set.class, GenreDTO.class);

        Set<GenreDTO> genres = objectMapper.readValue(resource.getFile(), type);
        genreService.saveAll(genres);
    }
}
