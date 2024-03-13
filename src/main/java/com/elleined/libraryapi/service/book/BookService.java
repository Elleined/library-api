package com.elleined.libraryapi.service.book;

import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.exception.FieldAlreadyExistsException;
import com.elleined.libraryapi.exception.NotFoundException;
import com.elleined.libraryapi.model.Book;

import java.util.Collection;
import java.util.List;

public interface BookService {
    BookDTO getById(int id) throws NotFoundException;

    BookDTO getByTitle(String title) throws NotFoundException;

    BookDTO getByIsbn(String isbn) throws NotFoundException;

    List<BookDTO> getAllById(List<Integer> bookIds);

    List<BookDTO> getAllByGenre(String genreName);

    List<BookDTO> getAllByTitleFirstLetter(char firstLetter);

    List<BookDTO> getAll();

    List<BookDTO> getAll(int pageNumber, int pageSize);

    List<BookDTO> getAll(int pageNumber, int pageSize, String sortDirection, String sortProperty);

    List<BookDTO> saveAll(Collection<BookDTO> books);

    BookDTO save(BookDTO bookDTO) throws FieldAlreadyExistsException, NotFoundException;

    void update(int id, BookDTO bookDTO) throws FieldAlreadyExistsException, IllegalArgumentException;

    boolean isbnAlreadyExists(String isbn);

    boolean isGenreNotValid(List<String> genres);

    default void addBookViewCount(Book book) {
        book.setViews(book.getViews() + 1);
        bookRepository.save(book);
    }
}
