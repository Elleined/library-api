package com.elleined.libraryapi.hateoas;

import com.elleined.libraryapi.controller.AuthorController;
import com.elleined.libraryapi.controller.BookController;
import com.elleined.libraryapi.dto.AuthorDTO;
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
public class AuthorHateoasAssembler extends HateoasAssembler<AuthorDTO> {
    private final Faker faker;

    @Override
    protected List<Link> getAllRelatedLinks(AuthorDTO dto, boolean doInclude) {
        return List.of(
                linkTo(methodOn(BookController.class)
                        .getAll(dto.getId(), 1, 5, Sort.Direction.ASC, "id", doInclude))
                        .withRel("books")
                        .withTitle("Get all books")
                        .withType(HttpMethod.GET.name())
        );
    }

    @Override
    protected List<Link> getAllSelfLinks(AuthorDTO dto, boolean doInclude) {
        return List.of(
                linkTo(methodOn(AuthorController.class)
                        .getById(dto.getId(), doInclude))
                        .withSelfRel()
                        .withTitle("Get by id")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(AuthorController.class)
                        .getAll(1, 5, Sort.Direction.ASC, "id", doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(AuthorController.class)
                        .save(faker.name().fullName(), faker.lorem().sentence(), doInclude))
                        .withSelfRel()
                        .withTitle("Save")
                        .withType(HttpMethod.POST.name()),

                linkTo(methodOn(AuthorController.class)
                        .update(dto.getId(), faker.name().fullName(), faker.lorem().sentence(), doInclude))
                        .withSelfRel()
                        .withTitle("Update")
                        .withTitle(HttpMethod.PUT.name()),

                linkTo(methodOn(AuthorController.class)
                        .getAllByFirstLetter('a', 1, 5, Sort.Direction.ASC, "id", doInclude))
                        .withSelfRel()
                        .withTitle("Get all by name first letter")
                        .withType(HttpMethod.GET.name())
        );
    }
}
