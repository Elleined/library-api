package com.elleined.libraryapi.repository;

import com.elleined.libraryapi.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query("SELECT g FROM Genre g WHERE g.name LIKE CONCAT(:firstLetter, '%') ORDER BY name")
    Page<Genre> searchByFirstLetter(@Param("firstLetter") char firstLetter, Pageable pageable);
}