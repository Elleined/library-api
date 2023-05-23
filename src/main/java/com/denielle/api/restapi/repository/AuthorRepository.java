package com.denielle.api.restapi.repository;

import com.denielle.api.restapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("select a from Author a where a.name = ?1")
    Optional<Author> fetchByName(String name);
}