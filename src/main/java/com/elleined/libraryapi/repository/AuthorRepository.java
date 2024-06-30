package com.elleined.libraryapi.repository;

import com.elleined.libraryapi.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT a FROM Author a WHERE a.name LIKE CONCAT(:firstLetter, '%') ORDER BY name")
    Page<Author> searchByFirstLetter(@Param("firstLetter") char firstLetter, Pageable pageable);
}