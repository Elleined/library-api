package com.elleined.libraryapi.dto;

import com.elleined.libraryapi.controller.AuthorController;
import com.elleined.libraryapi.controller.BookController;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class AuthorDTO extends DTO {
    private String name;
    private String biography;

    @Builder
    public AuthorDTO(int id,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt,
                     String name,
                     String biography) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.biography = biography;
    }

    @Override
    public AuthorDTO addLinks(boolean doInclude) {
        super.addLinks(doInclude);

        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(boolean doInclude) {
        return List.of(
                linkTo(methodOn(BookController.class)
                        .getAll(this.getId(), 0, 0, null, null, doInclude))
                        .withRel("books")
                        .withTitle("Get all books")
                        .withType(HttpMethod.GET.name())
        );
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of(
                linkTo(methodOn(AuthorController.class)
                        .getById(this.getId(), doInclude))
                        .withSelfRel()
                        .withTitle("Get by id")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(AuthorController.class)
                        .getAll(0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(AuthorController.class)
                        .save(null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Save")
                        .withType(HttpMethod.POST.name()),

                linkTo(methodOn(AuthorController.class)
                        .update(this.getId(), null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Update")
                        .withTitle(HttpMethod.PUT.name()),

                linkTo(methodOn(AuthorController.class)
                        .getAllByFirstLetter('a', 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all by name first letter")
                        .withType(HttpMethod.GET.name())
        );
    }
}
