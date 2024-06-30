package com.elleined.libraryapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
