package com.elleined.libraryapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
}
