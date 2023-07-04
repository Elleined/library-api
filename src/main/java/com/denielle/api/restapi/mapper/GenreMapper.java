package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.GenreDTO;
import com.denielle.api.restapi.exception.NotFoundException;
import com.denielle.api.restapi.model.Genre;
import com.denielle.api.restapi.repository.GenreRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GenreMapper implements BaseMapper<GenreDTO, Genre> {

    @Autowired
    protected GenreRepository genreRepository;

    public abstract GenreDTO toDTO(Genre genre);

    @Mapping(target = "bookGenres", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract Genre toEntity(GenreDTO genreDTO);

    @Mapping(target = "bookGenres", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract Genre updateEntity(@MappingTarget Genre genre, GenreDTO genreDTO);

    protected List<String> genreToName(Set<Genre> genres) {
        return genres != null ? genres.stream()
                .map(Genre::getName)
                .toList() : null;
    }

    protected Set<Genre> nameToGenreEntity(List<String> genreNames) {
        return genreNames != null ? genreNames.stream()
                .map(name -> genreRepository.fetchByName(name).orElseThrow(() -> new NotFoundException("Genre with name of " + name + " does not exists")))
                .collect(Collectors.toSet()) : null;
    }
    @BeforeMapping
    protected void updateEntityBeforeMapping(@MappingTarget Genre genre, GenreDTO genreDTO) {
        genreDTO.setUpdatedAt(LocalDateTime.now());
    }
    @BeforeMapping
    protected void toEntityBeforeMapping(GenreDTO genreDTO) {
        genreDTO.setCreatedAt(LocalDateTime.now());
    }
}
