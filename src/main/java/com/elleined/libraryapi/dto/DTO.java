package com.elleined.libraryapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public abstract class DTO extends RepresentationModel<DTO> {
    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}