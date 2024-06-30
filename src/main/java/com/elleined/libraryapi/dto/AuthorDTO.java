package com.elleined.libraryapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AuthorDTO extends DTO {
    private String name;
    private String biography;
}
