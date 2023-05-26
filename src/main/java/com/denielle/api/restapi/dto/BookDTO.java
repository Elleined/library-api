package com.denielle.api.restapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
public class BookDTO {
    private int id;
    private String title;
    private String isbn;
    private String description;
    private LocalDate publishedDate;
    private int pages;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> genres;
    private int views;
    private int saleCount;
}
