package com.denielle.api.restapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthorDTO {
    private int id;
    private String name;
    private String biography;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // private List<String> bookList;
}
