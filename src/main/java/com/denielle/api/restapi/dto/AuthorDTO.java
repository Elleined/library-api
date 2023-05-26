package com.denielle.api.restapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AuthorDTO {
    private int id;

    @NotEmpty(message = "Author name cannot be null or empty")
    @NotBlank(message = "Author name cannot be whitespace only") // Triggers when pure white space
    private String name;

    @NotEmpty(message = "Author biography cannot be null or empty")
    @NotBlank(message = "Author biography cannot be whitespace only")
    private String biography;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> books;
    private int bookCount;
}
