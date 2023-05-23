package com.denielle.api.restapi.service;

import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.model.Book;
import com.denielle.api.restapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllById(List<Integer> booksId) {
        return bookRepository.findAllById(booksId);
    }
    public BookDTO convertToDTO(Book book) {
        return null;
    }
}
