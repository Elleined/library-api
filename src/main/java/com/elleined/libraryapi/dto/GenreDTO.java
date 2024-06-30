package com.elleined.libraryapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GenreDTO extends DTO {
    private String name;

    @Builder
    public GenreDTO(int id, LocalDateTime createdAt, LocalDateTime updatedAt, String name) {
        super(id, createdAt, updatedAt);
        this.name = name;
    }
}
