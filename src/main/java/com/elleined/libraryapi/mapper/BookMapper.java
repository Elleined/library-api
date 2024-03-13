package com.elleined.libraryapi.mapper;

import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.model.Book;
import com.elleined.libraryapi.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDate;
import java.util.Set;

@Mapper(componentModel = "spring", uses = GenreMapper.class)
public interface BookMapper extends CustomMapper<Book, BookDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "title", source = "title"),
            @Mapping(target = "isbn", source = "isbn"),
            @Mapping(target = "description", source = "description"),
            @Mapping(target = "publishedDate", source = "publishedDate"),
            @Mapping(target = "pages", source = "pages"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "updatedAt", source = "updatedAt"),
            @Mapping(target = "authorId", source = "author.id"),
            @Mapping(target = "genreIds", expression = "java(book.getGenreIds())")
    })
    BookDTO toDTO(Book book);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "views", expression = "java(0)"),
            @Mapping(target = "title", expression = "java(title)"),
            @Mapping(target = "isbn", expression = "java(isbn)"),
            @Mapping(target = "description", expression = "java(description)"),
            @Mapping(target = "publishedDate", expression = "java(publishedDate)"),
            @Mapping(target = "pages", expression = "java(pages)"),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "author", expression = "java(author)"),
            @Mapping(target = "genres", expression = "java(genres)")
    })
    Book toEntity(String title,
                  String isbn,
                  String description,
                  LocalDate publishedDate,
                  int pages,
                  Author author,
                  Set<Genre> genres);
}
