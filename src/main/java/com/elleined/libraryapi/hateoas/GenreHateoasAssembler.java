package com.elleined.libraryapi.hateoas;

import com.elleined.libraryapi.dto.GenreDTO;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreHateoasAssembler extends HateoasAssembler<GenreDTO> {
    @Override
    protected List<Link> getAllRelatedLinks(GenreDTO dto, boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(GenreDTO dto, boolean doInclude) {
        return List.of();
    }
}
