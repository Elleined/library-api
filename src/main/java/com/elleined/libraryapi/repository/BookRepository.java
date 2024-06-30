package com.elleined.libraryapi.repository;

import com.elleined.libraryapi.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT(:firstLetter, '%') ORDER BY title")
    Page<Book> getAllByTitleFirstLetter(@Param("firstLetter") char firstLetter, Pageable pageable);
}