package com.elleined.libraryapi.mapper;

import com.elleined.libraryapi.dto.GenreDTO;
import com.elleined.libraryapi.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GenreMapper extends CustomMapper<Genre, GenreDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "updatedAt", source = "updatedAt"),
            @Mapping(target = "bookIds", expression = "java(genre.getBookIds())"),
    })
    GenreDTO toDTO(Genre genre);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "books", expression = "java(new java.util.ArrayList<>())"),
    })
    Genre toEntity();
}
