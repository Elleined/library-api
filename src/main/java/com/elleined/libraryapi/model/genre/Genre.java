package com.elleined.libraryapi.model.genre;

import com.elleined.libraryapi.model.Book;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(
        name = "genre",
        indexes = @Index(name = "genre_name_idx", columnList = "name")
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Genre {

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
            unique = true,
            nullable = false
    )
    private String name;

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

    @ManyToMany(mappedBy = "genres")
    private Set<Book> bookGenres;
}
