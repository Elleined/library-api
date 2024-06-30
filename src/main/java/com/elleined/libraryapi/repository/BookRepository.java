package com.elleined.libraryapi.repository;

import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT(:firstLetter, '%') ORDER BY title")
    Page<Book> findAllByTitleFirstLetter(@Param("firstLetter") char firstLetter, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.author = :author")
    Page<Book> findAll(@Param("author") Author author, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g = :genre")
    Page<Book> findAllByGenre(@Param("genre") Genre genre, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    Optional<Book> findByISBN(@Param("isbn") String isbn);
}