package com.denielle.authorbookgenreapi.model;

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
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "date_created")
    private LocalDateTime createdAt;

    @Column(name = "date_updated")
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "genres")
    @Setter(AccessLevel.NONE)
    private Set<Book> bookGenres;
}
