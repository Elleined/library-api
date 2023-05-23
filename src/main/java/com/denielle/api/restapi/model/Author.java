package com.denielle.api.restapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "author",
        indexes = @Index(name = "author_name_idx", columnList = "name")
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "biography", columnDefinition = "MEDIUMTEXT")
    private String biography;

    @Column(name = "date_created")
    private LocalDateTime createdAt;

    @Column(name = "date_updated")
    private LocalDateTime updatedAt;

    // author id reference is in book table
    @OneToMany(mappedBy = "author")
    private List<Book> bookList;
}
