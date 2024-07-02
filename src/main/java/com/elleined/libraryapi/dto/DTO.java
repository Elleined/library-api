package com.elleined.libraryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public abstract class DTO extends HateoasDTO {
    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}