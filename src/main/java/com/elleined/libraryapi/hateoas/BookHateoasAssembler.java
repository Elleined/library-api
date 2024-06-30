package com.elleined.libraryapi.hateoas;

import com.elleined.libraryapi.dto.BookDTO;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookHateoasAssembler extends HateoasAssembler<BookDTO> {
    @Override
    protected List<Link> getAllRelatedLinks(BookDTO dto, boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(BookDTO dto, boolean doInclude) {
        return List.of();
    }
}
