package com.elleined.libraryapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class GenreDTO {
    private int id;
    private String name;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    private Set<Integer> bookIds;
}
