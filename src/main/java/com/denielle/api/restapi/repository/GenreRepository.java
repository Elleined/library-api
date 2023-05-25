package com.denielle.api.restapi.repository;

import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query("select g from Genre g where g.name = ?1")
    Optional<Genre> fetchByName(String name);

    @Query(value = "SELECT * FROM author WHERE MATCH(name) AGAINST (CONCAT('+', :firstLetter, '*')) ORDER BY name ASC", nativeQuery = true)
    List<Genre> searchByFirstLetter(@Param("firstLetter") char firstLetter);
}