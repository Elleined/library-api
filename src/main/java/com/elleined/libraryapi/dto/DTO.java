package com.elleined.libraryapi.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class DTO extends RepresentationModel<DTO> {
    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}