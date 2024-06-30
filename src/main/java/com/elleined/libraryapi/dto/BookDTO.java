package com.elleined.libraryapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookDTO extends DTO {
    private String title;
    private String isbn;
    private String description;
    private LocalDate publishedDate;
    private int pages;
    private int views;

    private int authorId;

    @Builder
    public BookDTO(int id,
                   LocalDateTime createdAt,
                   LocalDateTime updatedAt,
                   String title,
                   String isbn,
                   String description,
                   LocalDate publishedDate,
                   int pages,
                   int views,
                   int authorId) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.isbn = isbn;
        this.description = description;
        this.publishedDate = publishedDate;
        this.pages = pages;
        this.views = views;
        this.authorId = authorId;
    }
}
