package com.elleined.bookauthorgenreapi.repository;

import com.elleined.bookauthorgenreapi.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query("select g from Genre g where g.name = ?1")
    Optional<Genre> fetchByName(String name);

    @Query("SELECT g.name FROM Genre g WHERE g.name LIKE CONCAT(:firstLetter, '%') ORDER BY name")
    List<String> searchByFirstLetter(@Param("firstLetter") char firstLetter);
}