package com.denielle.authorbookgenreapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AuthorDTO {
    private int id;

    @NotBlank(message = "Author name cannot be null or empty") // Triggers when pure white space
    private String name;

    @NotBlank(message = "Author biography cannot be null or empty")
    private String biography;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> books;
    private int bookCount;
}
