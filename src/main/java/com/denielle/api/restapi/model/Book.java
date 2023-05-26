package com.denielle.api.restapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

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
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn", unique = true)
    private String isbn;

    @Column(name = "description", columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "pages")
    private int pages;

    // For sorting mechanism
    @Column(name = "views")
    private int views;

    @Column(name = "sale_count")
    private int saleCount;

    @Column(name = "date_created")
    private LocalDateTime createdAt;

    @Column(name = "date_updated")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(
            name = "author_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_author_id")
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
}
