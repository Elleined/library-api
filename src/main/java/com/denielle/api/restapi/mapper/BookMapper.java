package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.model.Book;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Mapper(componentModel = "spring", uses = {GenreMapper.class, AuthorMapper.class})
public abstract class BookMapper implements BaseMapper<BookDTO, Book> {
    @Mapping(target = "authorName", source = "author.name")
    @Mapping(target = "genres", source = "genres")
    public abstract BookDTO toDTO(Book book);

    @Mapping(target = "author", source = "authorName")
    @Mapping(target = "genres", source = "genres")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Book toEntity(BookDTO bookDTO);

    @Mapping(target = "author", source = "authorName")
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

    @BeforeMapping
    protected void toEntityBeforeMapping(BookDTO bookDTO) {
        bookDTO.setCreatedAt(LocalDateTime.now());
    }

    @BeforeMapping
    protected void updateEntityBeforeMapping(@MappingTarget Book book, BookDTO bookDTO) {
        bookDTO.setUpdatedAt(LocalDateTime.now());
    }
}
