package com.denielle.api.restapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreDTO {
    private int id;
    private String name;
}
