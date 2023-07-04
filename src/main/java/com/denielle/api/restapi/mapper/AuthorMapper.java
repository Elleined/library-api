package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.model.Author;
import com.denielle.api.restapi.service.GenreService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@org.mapstruct.Mapper(componentModel = "spring", uses = BookMapper.class)
public abstract class AuthorMapper implements Mapper<AuthorDTO, Author> {

    @Autowired
    protected GenreService genreService;

    @Mapping(target = "books", source = "bookList")
    public abstract AuthorDTO toDTO(Author author);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "bookCount", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "bookList", ignore = true)
    })
    public abstract Author toEntity(AuthorDTO authorDTO);

    @Override
    @BeforeMapping
    public void toEntityBeforeMapping(AuthorDTO authorDTO) {
        System.out.println("\n ===toEntity before mapping===");

        System.out.println("AuthorDTO " + authorDTO);

        System.out.println("===End of toEntity before mapping \n");
    }

    @Override
    @BeforeMapping
    public void toDTOBeforeMapping(Author author) {
        System.out.println("\n ===toDTO before mapping===");

        System.out.println("Author " + author);

        System.out.println("===End of toDTO before mapping \n");
    }

    @Override
    @AfterMapping
    public void toEntityAfterMapping(AuthorDTO authorDTO) {
        System.out.println(genreService.getById(1) + " Genre serivec ");
        System.out.println("\n ===toEntity after mapping===");

        System.out.println("AuthorDTO " + authorDTO);

        System.out.println("===End of toEntity after mapping \n");
    }

    @Override
    @AfterMapping
    public void toDTOAfterMapping(Author author) {
        System.out.println("\n ===toDTO after mapping===");

        System.out.println("Author " + author);

        System.out.println("===End of toDTO after mapping \n");
    }
}
