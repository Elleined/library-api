package com.elleined.libraryapi.repository;

import com.elleined.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT(:firstLetter, '%') ORDER BY title")
    List<Book> getAllByTitleFirstLetter(@Param("firstLetter") char firstLetter);
}