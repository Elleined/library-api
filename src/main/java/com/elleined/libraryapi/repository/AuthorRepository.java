package com.elleined.libraryapi.repository;

import com.elleined.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT a FROM Author a WHERE a.name LIKE CONCAT(:firstLetter, '%') ORDER BY name")
    List<Author> searchByFirstLetter(@Param("firstLetter") char firstLetter);
}