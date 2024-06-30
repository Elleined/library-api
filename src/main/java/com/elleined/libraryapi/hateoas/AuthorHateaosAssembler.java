package com.elleined.libraryapi.hateoas;

import com.elleined.libraryapi.dto.AuthorDTO;
import com.elleined.libraryapi.model.Author;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorHateaosAssembler extends HateoasAssembler<AuthorDTO> {

    @Override
    protected List<Link> getAllRelatedLinks(AuthorDTO dto, boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(AuthorDTO dto, boolean doInclude) {
        return List.of();
    }
}
