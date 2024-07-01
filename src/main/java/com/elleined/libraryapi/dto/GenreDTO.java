package com.elleined.libraryapi.dto;

import com.elleined.libraryapi.controller.GenreController;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class GenreDTO extends DTO {
    private String name;

    @Builder
    public GenreDTO(int id, LocalDateTime createdAt, LocalDateTime updatedAt, String name) {
        super(id, createdAt, updatedAt);
        this.name = name;
    }

    @Override
    public GenreDTO addLinks(boolean doInclude) {
        super.addLinks(doInclude);

        return this;
    }

    @Override
    protected List<Link> getAllRelatedLinks(boolean doInclude) {
        return List.of();
    }

    @Override
    protected List<Link> getAllSelfLinks(boolean doInclude) {
        return List.of(
                linkTo(methodOn(GenreController.class)
                        .getById(this.getId(), doInclude))
                        .withSelfRel()
                        .withTitle("Get by id")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(GenreController.class)
                        .getAll(0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(GenreController.class)
                        .save(null, doInclude))
                        .withSelfRel()
                        .withTitle("Save")
                        .withType(HttpMethod.POST.name()),

                linkTo(methodOn(GenreController.class)
                        .update(this.getId(), null, doInclude))
                        .withSelfRel()
                        .withTitle("Update")
                        .withTitle(HttpMethod.PUT.name()),

                linkTo(methodOn(GenreController.class)
                        .getAllByFirstLetter('a', 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all by name first letter")
                        .withType(HttpMethod.GET.name())
        );
    }
}
