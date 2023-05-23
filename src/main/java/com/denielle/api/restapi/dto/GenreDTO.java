package com.denielle.api.restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GenreDTO {
    private int id;
    private String name;

    @JsonIgnore
    private LocalDateTime createdAt;
}
