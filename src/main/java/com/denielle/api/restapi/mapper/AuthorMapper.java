package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.service.GenreService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public abstract class AuthorMapper implements BaseMapper<AuthorDTO, Author> {

    @Autowired
    protected GenreService genreService;
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
    public abstract Author updateAuthor(AuthorDTO authorDTO, @MappingTarget Author author);

    @BeforeMapping
    protected void beforeUpdateAuthor(AuthorDTO authorDTO, @MappingTarget Author author) {
        authorDTO.setUpdatedAt(LocalDateTime.now());
        // Execute code before updating the author
    }

    @AfterMapping
    protected void afterUpdateAuthor(AuthorDTO authorDTO, @MappingTarget Author author) {
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
