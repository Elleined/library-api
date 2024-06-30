package com.elleined.libraryapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BookDTO extends DTO {
    private String title;
    private String isbn;
    private String description;
    private LocalDate publishedDate;
    private int pages;
    private int views;

    private int authorId;
}
