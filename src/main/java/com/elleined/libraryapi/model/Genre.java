package com.elleined.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(
        name = "tbl_genre",
        indexes = {
                @Index(name = "created_at_idx", columnList = "created_at"),
                @Index(name = "name_idx", columnList = "name"),
        }
)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Genre extends PrimaryKeyIdentity {

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books;
}
