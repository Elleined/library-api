package com.denielle.api.restapi.repository;

import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b where b.isbn = ?1")
    Optional<Book> fetchByIsbn(String isbn);
    @Query("select b from Book b where b.title = ?1")
    Optional<Book> fetchByTitle(String title);

}