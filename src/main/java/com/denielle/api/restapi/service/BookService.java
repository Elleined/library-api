package com.denielle.api.restapi.service;

import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.exception.NotFoundException;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.model.Book;
import com.denielle.api.restapi.model.Genre;
import com.denielle.api.restapi.repository.AuthorRepository;
import com.denielle.api.restapi.repository.BookRepository;
import com.denielle.api.restapi.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookDTO getById(int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book with id of " + bookId + " does not exists"));
        return this.convertToDTO(book);
    }

    public BookDTO getByTitle(String bookTitle) {
        Book book = bookRepository.fetchByTitle(bookTitle).orElseThrow(() -> new NotFoundException("Book with title of " + bookTitle + " does not exits"));
        return this.convertToDTO(book);
    }

    public BookDTO getByIsbn(String isbn) {
        Book book = bookRepository.fetchByIsbn(isbn).orElseThrow(() -> new NotFoundException("Book with isbn of " + isbn + " does not exits"));
        return this.convertToDTO(book);
    }

    public List<BookDTO> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<BookDTO> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);

        return bookRepository.findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<BookDTO> getAll(int pageNumber, int pageSize, Sort.Direction direction, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, direction, sortProperty);

        return bookRepository.findAll(pageable)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public int save(BookDTO bookDTO) {
        Author author = authorRepository.fetchByName(bookDTO.getAuthorName()).orElseThrow(() -> new NotFoundException("Author with name of " + bookDTO.getAuthorName() + " does not exists"));

        Set<Genre> genres = bookDTO.getGenres()
                .stream()
                .map(name -> genreRepository.fetchByName(name)
                        .orElseThrow(() -> new NotFoundException("Genre with name of " + name + " does not exists")))
                .collect(Collectors.toSet());

        Book book = Book.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .description(bookDTO.getDescription())
                .isbn(bookDTO.getIsbn())
                .pages(bookDTO.getPages())
                .publishedDate(bookDTO.getPublishedDate())
                .createdAt(bookDTO.getCreatedAt())
                .updatedAt(bookDTO.getUpdatedAt())
                .author(author)
                .genres(genres)
                .build();

        bookRepository.save(book);
        log.debug("Book saved successfully {}", book.getId());
        return book.getId();
    }


    public BookDTO convertToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .pages(book.getPages())
                .publishedDate(book.getPublishedDate())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .authorName(book.getAuthor().getName())
                .genres(book.getGenres()
                        .stream()
                        .map(Genre::getName)
                        .toList())
                .build();
    }
}
