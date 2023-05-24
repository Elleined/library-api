package com.denielle.api.restapi.repository;

import com.denielle.api.restapi.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query("select g from Genre g where g.name = ?1")
    Optional<Genre> fetchByName(String name);
}