package com.denielle.api.restapi.repository;

import com.denielle.api.restapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}