package com.denielle.api.restapi.repository;

import com.denielle.api.restapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("select a from Author a where a.name = ?1")
    Optional<Author> fetchByName(String name);

    @Query("SELECT a.name FROM Author a WHERE a.name LIKE CONCAT(:firstLetter, '%') ORDER BY name")
    List<String> searchByFirstLetter(@Param("firstLetter") char firstLetter);
}