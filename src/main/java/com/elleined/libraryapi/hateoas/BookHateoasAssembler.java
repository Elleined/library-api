package com.elleined.libraryapi.hateoas;

import com.elleined.libraryapi.controller.BookController;
import com.elleined.libraryapi.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class BookHateoasAssembler extends HateoasAssembler<BookDTO> {
    private final Faker faker;

    @Override
    protected List<Link> getAllRelatedLinks(BookDTO dto, boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(BookDTO dto, boolean doInclude) {
        return List.of(
                linkTo(methodOn(BookController.class)
                        .getById(dto.getId(), doInclude))
                        .withSelfRel()
                        .withTitle("Get by id")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(BookController.class)
                        .getByIsbn(dto.getIsbn(), doInclude))
                        .withSelfRel()
                        .withTitle("Get by ISBN")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(BookController.class)
                        .getAll(dto.getId(), 1, 5, Sort.Direction.ASC, "id", doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(BookController.class)
                        .save(faker.book().title(), faker.code().isbn10(), faker.lorem().sentence(), faker.date().birthday().toLocalDateTime().toLocalDate(), faker.number().randomDigit(), dto.getAuthorId(), new HashSet<>(), doInclude))
                        .withSelfRel()
                        .withTitle("Save")
                        .withType(HttpMethod.POST.name()),

                linkTo(methodOn(BookController.class)
                        .update(dto.getId(), faker.book().title(), faker.lorem().sentence(), faker.number().randomDigit(), new HashSet<>(), doInclude))
                        .withSelfRel()
                        .withTitle("Update")
                        .withTitle(HttpMethod.PUT.name()),

                linkTo(methodOn(BookController.class)
                        .getAllByTitleFirstLetter('a', 1, 5, Sort.Direction.ASC, "id", doInclude))
                        .withSelfRel()
                        .withTitle("Get all by title first letter")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(BookController.class)
                        .getAllByGenre(faker.number().randomDigit(), 1, 5, Sort.Direction.ASC, "id", doInclude))
                        .withSelfRel()
                        .withTitle("Get all by genre name")
                        .withType(HttpMethod.GET.name())
        );
    }
}
