package com.elleined.libraryapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

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
}
