package com.elleined.libraryapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "tbl_author",
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
    @Column(
            name = "id",
            nullable = false,
            unique = true,
            updatable = false
    )
    private int id;

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @Column(
            name = "biography",
            nullable = false,
            columnDefinition = "MEDIUMTEXT")
    private String biography;

    @Column(
            name = "date_created",
            nullable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "date_updated",
            nullable = false
    )
    private LocalDateTime updatedAt;

    // author id reference is in book table
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public List<Integer> getBookIds() {
        return this.getBooks().stream()
                .map(Book::getId)
                .toList();
    }
}
