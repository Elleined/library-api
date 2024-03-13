package com.elleined.libraryapi.model;

import com.elleined.libraryapi.model.genre.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "book",
        indexes = @Index(name = "book_title_idx", columnList = "title")
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {

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
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "isbn",
            unique = true,
            nullable = false
    )
    private String isbn;

    @Column(
            name = "description",
            columnDefinition = "MEDIUMTEXT",
            nullable = false
    )
    private String description;

    @Column(
            name = "published_date",
            nullable = false
    )
    private LocalDate publishedDate;

    @Column(
            name = "pages",
            nullable = false
    )
    private int pages;

    // For sorting mechanism
    @Column(name = "views")
    private int views;

    @Column(name = "date_created")
    private LocalDateTime createdAt;

    @Column(name = "date_updated")
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "author_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_author_id"),
            nullable = false
    )
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(
                    name = "book_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "FK_book_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "genre_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "FK_genre_id")
            )
    )
    private Set<Genre> genres;

    public Set<Integer> getGenreIds() {
        return this.getGenres().stream()
                .map(Genre::getId)
                .collect(Collectors.toSet());
    }
}
