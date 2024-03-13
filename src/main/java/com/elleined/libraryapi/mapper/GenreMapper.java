package com.elleined.libraryapi.mapper;

import com.elleined.libraryapi.dto.GenreDTO;
import com.elleined.libraryapi.exception.NotFoundException;
import com.elleined.libraryapi.model.genre.Genre;
import com.elleined.libraryapi.repository.GenreRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface GenreMapper extends CustomMapper<Genre, GenreDTO> {

    @Override
    @Mappings({

    })
    GenreDTO toDTO(Genre genre);

    Genre toEntity();
}
