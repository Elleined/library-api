package com.elleined.libraryapi.dto;

import com.elleined.libraryapi.controller.BookController;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class BookDTO extends DTO {
    private String title;
    private String isbn;
    private String description;
    private LocalDate publishedDate;
    private int pages;
    private int views;

    private AuthorDTO authorDTO;

    @Builder
    public BookDTO(int id, LocalDateTime createdAt, LocalDateTime updatedAt, String isbn, String title, String description, LocalDate publishedDate, int pages, int views, AuthorDTO authorDTO) {
        super(id, createdAt, updatedAt);
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.pages = pages;
        this.views = views;
        this.authorDTO = authorDTO;
    }

    @Override
    public BookDTO addLinks(boolean doInclude) {
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
                linkTo(methodOn(BookController.class)
                        .getById(this.getId(), doInclude))
                        .withSelfRel()
                        .withTitle("Get by id")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(BookController.class)
                        .getByIsbn(this.getIsbn(), doInclude))
                        .withSelfRel()
                        .withTitle("Get by ISBN")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(BookController.class)
                        .getAll(this.getId(), 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(BookController.class)
                        .save(null, null, null, null, 0, 0, null, doInclude))
                        .withSelfRel()
                        .withTitle("Save")
                        .withType(HttpMethod.POST.name()),

                linkTo(methodOn(BookController.class)
                        .update(this.getId(), null, null, 0, null, doInclude))
                        .withSelfRel()
                        .withTitle("Update")
                        .withTitle(HttpMethod.PUT.name()),

                linkTo(methodOn(BookController.class)
                        .getAllByTitleFirstLetter('a', 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all by title first letter")
                        .withType(HttpMethod.GET.name()),

                linkTo(methodOn(BookController.class)
                        .getAllByGenre(0, 0, 0, null, null, doInclude))
                        .withSelfRel()
                        .withTitle("Get all by genre name")
                        .withType(HttpMethod.GET.name())
        );
    }
}
