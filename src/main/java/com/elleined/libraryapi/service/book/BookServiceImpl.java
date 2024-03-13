package com.elleined.libraryapi.service.book;

import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.exception.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.NotFoundException;
import com.elleined.libraryapi.mapper.BookMapper;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private static final Random random = new Random();

    @Override
    public BookDTO getById(int id) throws NotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book with id of " + id + " does not exists"));
        this.addBookViewCount(book);

        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO getByTitle(String title) throws NotFoundException {
        Book book = bookRepository.fetchByTitle(title).orElseThrow(() -> new NotFoundException("Book with title of " + title + " does not exits"));
        this.addBookViewCount(book);

        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO getByIsbn(String isbn) throws NotFoundException {
        Book book = bookRepository.fetchByIsbn(isbn).orElseThrow(() -> new NotFoundException("Book with isbn of " + isbn + " does not exits"));
        this.addBookViewCount(book);

        return bookMapper.toDTO(book);
    }

    @Override
    public List<BookDTO> getAllById(List<Integer> bookIds) {
        return bookIds.stream()
                .map(this::getById)
                .toList();
    }

    @Override
    public List<BookDTO> getAllByGenre(String genreName) {
        List<Book> books = bookRepository.getAllByGenre(genreName);
        books.forEach(this::addBookViewCount);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public List<BookDTO> getAllByTitleFirstLetter(char firstLetter) {
        List<Book> books = bookRepository.getAllByTitleFirstLetter(firstLetter);
        books.forEach(this::addBookViewCount);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public List<BookDTO> getAll() {
        List<Book> books = bookRepository.findAll();
        books.forEach(this::addBookViewCount);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public List<BookDTO> getAll(int pageNumber, int pageSize) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize);

        List<Book> books = bookRepository.findAll(pageable).toList();
        books.forEach(this::addBookViewCount);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public List<BookDTO> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty) {
        Pageable pageable = PageSorter.getPage(pageNumber, pageSize, sortDirection, sortProperty);

        List<Book> books = bookRepository.findAll(pageable).toList();
        books.forEach(this::addBookViewCount);

        return books.stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @Override
    public List<BookDTO> saveAll(Collection<BookDTO> books) {
        return books.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public BookDTO save(BookDTO bookDTO) throws FieldAlreadyExistsException, NotFoundException {
        if (isbnAlreadyExists(bookDTO.getIsbn())) throw new FieldAlreadyExistsException("Book with isbn of " + bookDTO.getIsbn() + " already exists!");
        if (isGenreNotValid(bookDTO.getGenres())) throw new NullPointerException("Genre cannot be null or empty or blank");

        Book book = bookMapper.toEntity(bookDTO);
        book.setSaleCount(random.nextInt(999));

        Book savedBook = bookRepository.save(book);
        log.debug("Book saved successfully {}", book.getTitle());
        return bookMapper.toDTO( savedBook );
    }

    @Override
    public void update(int id, BookDTO bookDTO) throws FieldAlreadyExistsException, IllegalArgumentException {
        if (isbnAlreadyExists(bookDTO.getIsbn())) throw new FieldAlreadyExistsException("Book with isbn of " + bookDTO.getIsbn() + " already exists!");
        if (isGenreNotValid(bookDTO.getGenres())) throw new IllegalArgumentException("Genre cannot be null or empty or blank");

        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book with id of " + id + " does not exists"));
        bookMapper.updateEntity(book, bookDTO);
        bookRepository.save(book);
        log.debug("Book updated successfully");
    }

    @Override
    public boolean isbnAlreadyExists(String isbn) {
        return bookRepository.findAll()
                .stream()
                .map(Book::getIsbn)
                .anyMatch(isbn::equalsIgnoreCase);
    }

    @Override
    public boolean isGenreNotValid(List<String> genres) {
        return genres.stream().anyMatch(StringValidator::validate);
    }
}
