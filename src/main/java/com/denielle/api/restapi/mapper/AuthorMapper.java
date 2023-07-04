package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.service.AuthorService;
import com.denielle.api.restapi.service.GenreService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public abstract class AuthorMapper implements BaseMapper<AuthorDTO, Author> {

    @Autowired
    protected GenreService genreService;

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

    @BeforeMapping
    protected void toDTOBeforeMapping(Author author) {
        System.out.println("\n ===toDTO before mapping===");

        System.out.println("Author " + author);

        System.out.println("===End of toDTO before mapping \n");
    }

    @BeforeMapping
    protected void toDTOBeforeMapping(Author author, @Context String anyObjectToUse) {

    }

    @AfterMapping
    protected void toDTOAfterMapping(Author author) {
        System.out.println("\n ===toDTO after mapping===");

        System.out.println("Author " + author);

        System.out.println("===End of toDTO after mapping \n");
    }

    @AfterMapping
    protected void toDTOAfterMapping(Author author, @Context String anyObjectToUse) {

    }

    @BeforeMapping
    protected void toEntityBeforeMapping(AuthorDTO authorDTO) {
        System.out.println("\n ===toEntity before mapping===");

        System.out.println("AuthorDTO " + authorDTO);

        System.out.println("===End of toEntity before mapping \n");
    }

    @BeforeMapping
    protected void toEntityBeforeMapping(AuthorDTO authorDTO, @Context String anyObjectToUse) {
    }

    @AfterMapping
    protected void toEntityAfterMapping(AuthorDTO authorDTO) {
        System.out.println(genreService.getById(1) + " Genre serivec ");
        System.out.println("\n ===toEntity after mapping===");

        System.out.println("AuthorDTO " + authorDTO);

        System.out.println("===End of toEntity after mapping \n");
    }

    @AfterMapping
    protected void toEntityAfterMapping(AuthorDTO authorDTO, @Context String anyObjectToUse) {

    }
}
