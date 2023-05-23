package com.denielle.api.restapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDTO {
    private int id;
    private String name;
    private String biography;
    // private List<BookDTO> bookList;
}
