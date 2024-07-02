package com.elleined.libraryapi.mapper;

import com.elleined.libraryapi.dto.AuthorDTO;
import com.elleined.libraryapi.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends CustomMapper<Author, AuthorDTO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "updatedAt", source = "updatedAt"),

            @Mapping(target = "name", source = "name"),
            @Mapping(target = "biography", source = "biography"),
    })
    AuthorDTO toDTO(Author author);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())"),

            @Mapping(target = "name", expression = "java(name)"),
            @Mapping(target = "biography", expression = "java(biography)"),

            @Mapping(target = "books", expression = "java(new java.util.ArrayList<>())")
    })
    Author toEntity(String name,
                    String biography);
}
