package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface AuthorMapper {

    @Mapping(target = "books", source = "bookList")
    AuthorDTO toDTO(Author author);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "bookCount", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookList", ignore = true)
    Author toEntity(AuthorDTO authorDTO);
}
