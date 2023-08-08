package com.denielle.authorbookgenreapi.service;

import com.denielle.authorbookgenreapi.dto.BookDTO;
import com.denielle.authorbookgenreapi.exception.FieldAlreadyExistsException;
import com.denielle.authorbookgenreapi.exception.NotFoundException;
import com.denielle.authorbookgenreapi.mapper.BookMapper;
import com.denielle.authorbookgenreapi.model.Book;
import com.denielle.authorbookgenreapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private static final Random random = new Random();

    public BookDTO getById(int id) throws NotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book with id of " + id + " does not exists"));
        this.addBookViewCount(book);

        return bookMapper.toDTO(book);
    }

    public BookDTO getByTitle(String title) throws NotFoundException {
        Book book = bookRepository.fetchByTitle(title).orElseThrow(() -> new NotFoundException("Book with title of " + title + " does not exits"));
        this.addBookViewCount(book);

        return bookMapper.toDTO(book);
    }

    public BookDTO getByIsbn(String isbn) throws NotFoundException {
        Book book = bookRepository.fetchByIsbn(isbn).orElseThrow(() -> new NotFoundException("Book with isbn of " + isbn + " does not exits"));
        this.addBookViewCount(book);

        return bookMapper.toDTO(book);
    }

    public List<BookDTO> getAllById(List<Integer> bookIds) {
        return bookIds.stream()
                .map(this::getById)
                .toList();
    }

    public List<BookDTO> getAllByGenre(String genreName) {
        List<Book> books = bookRepository.getAllByGenre(genreName);
        books.forEach(this::addBookViewCount);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    public List<BookDTO> getAllByTitleFirstLetter(char firstLetter) {
        List<Book> books = bookRepository.getAllByTitleFirstLetter(firstLetter);
        books.forEach(this::addBookViewCount);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        books.forEach(this::addBookViewCount);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    public List<BookDTO> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);

        List<Book> books = bookRepository.findAll(pageable).toList();
        books.forEach(this::addBookViewCount);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    public List<BookDTO> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, sortDirection, sortProperty);

        List<Book> books = bookRepository.findAll(pageable).toList();
        books.forEach(this::addBookViewCount);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    public List<BookDTO> saveAll(List<BookDTO> books) {
        return books.stream()
                .map(this::save)
                .toList();
    }

    public BookDTO save(BookDTO bookDTO) throws FieldAlreadyExistsException, NotFoundException {
        if (isbnAlreadyExists(bookDTO.getIsbn())) throw new FieldAlreadyExistsException("Book with isbn of " + bookDTO.getIsbn() + " already exists!");
        if (isGenreNotValid(bookDTO.getGenres())) throw new NullPointerException("Genre cannot be null or empty or blank");

        Book book = bookMapper.toEntity(bookDTO);
        book.setSaleCount(random.nextInt(999));

        Book savedBook = bookRepository.save(book);
        log.debug("Book saved successfully {}", book.getTitle());
        return bookMapper.toDTO( savedBook );
    }

    public void update(int id, BookDTO bookDTO) throws FieldAlreadyExistsException, IllegalArgumentException {
        if (isbnAlreadyExists(bookDTO.getIsbn())) throw new FieldAlreadyExistsException("Book with isbn of " + bookDTO.getIsbn() + " already exists!");
        if (isGenreNotValid(bookDTO.getGenres())) throw new IllegalArgumentException("Genre cannot be null or empty or blank");

        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book with id of " + id + " does not exists"));
        bookMapper.updateEntity(book, bookDTO);
        bookRepository.save(book);
        log.debug("Book updated successfully");
    }

    public boolean isbnAlreadyExists(String isbn) {
        return bookRepository.findAll()
                .stream()
                .map(Book::getIsbn)
                .anyMatch(isbn::equalsIgnoreCase);
    }

    public boolean isGenreNotValid(List<String> genres) {
        return genres.stream().anyMatch(StringValidator::validate);
    }
    private void addBookViewCount(Book book) {
        book.setViews(book.getViews() + 1);
        bookRepository.save(book);
    }
}
