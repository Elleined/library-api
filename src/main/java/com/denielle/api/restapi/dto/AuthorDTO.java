package com.denielle.api.restapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class AuthorDTO {
    private int id;
    private String name;
    private String biography;
    private List<String> bookList;
}
