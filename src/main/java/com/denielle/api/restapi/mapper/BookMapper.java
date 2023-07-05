package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.exception.NotFoundException;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.model.Book;
import com.denielle.api.restapi.repository.AuthorRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", uses = GenreMapper.class)
public abstract class BookMapper implements BaseMapper<BookDTO, Book> {

    @Autowired
    protected AuthorRepository authorRepository;

    @Mapping(target = "authorName", source = "author.name")
    @Mapping(target = "genres", source = "genres")
    public abstract BookDTO toDTO(Book book);

    @Mapping(target = "author", expression = "java(this.getByName(bookDTO.getAuthorName()))")
    @Mapping(target = "genres", source = "genres")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Book toEntity(BookDTO bookDTO);

    @Mapping(target = "author", expression = "java(this.getByName(bookDTO.getAuthorName()))")
    @Mapping(target = "genres", source = "genres")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "views", ignore = true)
    public abstract Book updateEntity(@MappingTarget Book book, BookDTO bookDTO);

    protected List<String> mapBookTitle(List<Book> books) {
        return books != null ? books.stream()
                .map(Book::getTitle)
                .toList() : null;
    }

    protected int getBookCount(Author author) {
        return author.getBookList() != null ? author.getBookList().size() : 0;
    }

    protected Author getByName(String authorName) throws NotFoundException {
        return authorRepository.fetchByName(authorName).orElseThrow(() -> new NotFoundException("Author with name of " + authorName + " does not exists!"));
    }

    @BeforeMapping
    protected void toEntityBeforeMapping(BookDTO bookDTO) {
        bookDTO.setCreatedAt(LocalDateTime.now());
    }

    @BeforeMapping
    protected void updateEntityBeforeMapping(@MappingTarget Book book, BookDTO bookDTO) {
        bookDTO.setUpdatedAt(LocalDateTime.now());
    }
}
