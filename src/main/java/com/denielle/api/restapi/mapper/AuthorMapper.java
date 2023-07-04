package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.service.GenreService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public abstract class AuthorMapper implements BaseMapper<AuthorDTO, Author> {

    @Autowired
    protected GenreService genreService;
    // Use other beans here and annotate with autowired to use
    
    @Mapping(target = "books", source = "bookList")
    public abstract AuthorDTO toDTO(Author author);

    @Mapping(target = "books", source = "bookList")
    public abstract AuthorDTO toDTO(Author author,
                                    @Context String anyObjectToUse);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "bookCount", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "bookList", ignore = true)
    })
    public abstract Author toEntity(AuthorDTO authorDTO);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "bookCount", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "bookList", ignore = true)
    })
    public abstract Author toEntity(AuthorDTO authorDTO,
                                    @Context String anyObjectToUse);

    @Mapping(target = "name", source = "name")
    public abstract Author updateAuthorName(String name, @MappingTarget Author author);

    @BeforeMapping
    protected void beforeUpdateAuthor(String name, @MappingTarget Author author) {
        // Execute code before updating the author name
    }

    @AfterMapping
    protected void afterUpdateAuthor(String name, @MappingTarget Author author) {
        // Execute code after updating the author name
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
