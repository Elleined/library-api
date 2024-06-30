package com.elleined.libraryapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "tbl_author",
        indexes = {
                @Index(name = "created_at_idx", columnList = "created_at"),
                @Index(name = "name_idx", columnList = "name"),
        }
)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Author extends PrimaryKeyIdentity {

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "biography",
            nullable = false
    )
    private String biography;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
