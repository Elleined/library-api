package com.denielle.api.restapi.model;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToMany(mappedBy = "genres")
    @Setter(AccessLevel.NONE)
    private Set<Book> bookGenres;
}
