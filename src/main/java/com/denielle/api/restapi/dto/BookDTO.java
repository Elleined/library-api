package com.denielle.api.restapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
@Data
@Builder
public class BookDTO {
    private int id;

    private String title;

    private String isbn;

    private String description;

    private LocalDate publishedDate;

    private int pages;

    private String authorName;

    private Set<GenreDTO> genres;
}
