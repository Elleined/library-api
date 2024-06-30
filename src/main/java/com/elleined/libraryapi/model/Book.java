package com.elleined.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(
        name = "tbl_author",
        indexes = {
                @Index(name = "created_at_idx", columnList = "created_at"),
                @Index(name = "title_idx", columnList = "title"),
        }
)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Book extends PrimaryKeyIdentity {

    @Column(
            name = "title",
            nullable = false
    )
    private String title;

    @Column(
            name = "isbn",
            unique = true,
            nullable = false,
            updatable = false
    )
    private String isbn;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @Column(
            name = "published_date",
            nullable = false,
            updatable = false
    )
    private LocalDate publishedDate;

    @Column(
            name = "pages",
            nullable = false
    )
    private int pages;

    @Column(name = "views")
    private int views;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "author_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "tbl_book_genres",
            joinColumns = @JoinColumn(
                    name = "book_id",
                    referencedColumnName = "id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "genre_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    private Set<Genre> genres;
}
