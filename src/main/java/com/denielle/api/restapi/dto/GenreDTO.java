package com.denielle.api.restapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GenreDTO {
    private int id;
    private String name;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
