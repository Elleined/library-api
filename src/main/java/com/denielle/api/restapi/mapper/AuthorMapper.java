package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.exception.NotFoundException;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.repository.AuthorRepository;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public abstract class AuthorMapper implements BaseMapper<AuthorDTO, Author> {

    @Autowired
    protected AuthorRepository authorRepository;
    // Use other beans here and annotate with autowired to use

    @Mapping(target = "bookCount", source = "author")
    @Mapping(target = "books", source = "bookList")
    public abstract AuthorDTO toDTO(Author author);

    @Mapping(target = "bookCount", source = "author")
    @Mapping(target = "books", source = "bookList")
    public abstract AuthorDTO toDTO(Author author,
                                    @Context String anyObjectToUse);

    @Mappings({
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "bookList", ignore = true)
    })
    public abstract Author toEntity(AuthorDTO authorDTO);

    @Mappings({
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "bookList", ignore = true)
    })
    public abstract Author toEntity(AuthorDTO authorDTO,
                                    @Context String anyObjectToUse);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "bookList", ignore = true)
    })
    public abstract Author updateEntity(@MappingTarget Author author, AuthorDTO authorDTO);

    protected Author getByName(String authorName) {
        return authorRepository.fetchByName(authorName).orElseThrow(() -> new NotFoundException("Author with name of " + authorName + " does not exists"));
    }

    @BeforeMapping
    protected void beforeUpdateAuthor(@MappingTarget Author author, AuthorDTO authorDTO) {
        authorDTO.setUpdatedAt(LocalDateTime.now());
        // Execute code before updating the author
    }

    @AfterMapping
    protected void afterUpdateAuthor(@MappingTarget Author author, AuthorDTO authorDTO) {
        // Execute code after updating the author
    }

    @BeforeMapping
    protected void toDTOBeforeMapping(Author author) {
        // Execute code before creating AuthorDTO object
    }

    @BeforeMapping
    protected void toDTOBeforeMapping(Author author, @Context String anyObjectToUse) {
        // Execute code before creating AuthorDTO object with extra argument to use
    }

    @AfterMapping
    protected void toDTOAfterMapping(Author author) {
        // Execute code after creating AuthorDTO object
    }

    @AfterMapping
    protected void toDTOAfterMapping(Author author, @Context String anyObjectToUse) {
        // Execute code after creating AuthorDTO object with extra argument to use
    }

    @BeforeMapping
    protected void toEntityBeforeMapping(AuthorDTO authorDTO) {
        authorDTO.setCreatedAt(LocalDateTime.now());
        // Execute code before creating Author object
    }

    @BeforeMapping
    protected void toEntityBeforeMapping(AuthorDTO authorDTO, @Context String anyObjectToUse) {
        // Execute code before creating Author object with extra argument to use
    }

    @AfterMapping
    protected void toEntityAfterMapping(AuthorDTO authorDTO) {
        // Execute code after creating Author object
    }

    @AfterMapping
    protected void toEntityAfterMapping(AuthorDTO authorDTO, @Context String anyObjectToUse) {
        // Execute code after creating Author object with extra argument to use
    }
}
