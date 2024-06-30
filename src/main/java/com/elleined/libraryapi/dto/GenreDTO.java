package com.elleined.libraryapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class GenreDTO extends DTO {
    private String name;
}
