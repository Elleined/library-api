package com.elleined.libraryapi.hateoas;

import com.elleined.libraryapi.controller.GenreController;
import com.elleined.libraryapi.dto.GenreDTO;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class GenreHateoasAssembler extends HateoasAssembler<GenreDTO> {
    private final Faker faker;

    @Override
    protected List<Link> getAllRelatedLinks(GenreDTO dto, boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(GenreDTO dto, boolean doInclude) {
        return List.of(
                linkTo(methodOn(GenreController.class)
                        .getById(dto.getId(), doInclude))
                        .withSelfRel()
                        .withTitle("Get by id")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(GenreController.class)
                        .getAll(1, 5, Sort.Direction.ASC, "id", doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(GenreController.class)
                        .save(faker.name().fullName(), doInclude))
                        .withSelfRel()
                        .withTitle("Save")
                        .withType(HttpMethod.POST.name()),

                linkTo(methodOn(GenreController.class)
                        .update(dto.getId(), faker.name().fullName(), doInclude))
                        .withSelfRel()
                        .withTitle("Update")
                        .withTitle(HttpMethod.PUT.name()),

                linkTo(methodOn(GenreController.class)
                        .getAllByFirstLetter('a', 1, 5, Sort.Direction.ASC, "id", doInclude))
                        .withSelfRel()
                        .withTitle("Get all by name first letter")
                        .withType(HttpMethod.GET.name())
        );
    }
}
