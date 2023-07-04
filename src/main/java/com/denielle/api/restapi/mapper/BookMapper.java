package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.BookDTO;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.model.Book;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = GenreMapper.class)
public interface BookMapper {

    @Mapping(target = "authorName", source = "author.name")
    BookDTO toDTO(Book book);

    @InheritInverseConfiguration
    @Mapping(target = "genres", ignore = true)
    Book toEntity(BookDTO bookDTO);

    default List<String> mapBookTitle(List<Book> books) {
        if (books == null) return null;
        return books.stream()
                .map(Book::getTitle)
                .toList();
    }

    default int getBookCount(Author author) {
        return author.getBookList().size();
    }
}
