package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.GenreDTO;
import com.denielle.api.restapi.model.Genre;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDTO(Genre genre);

    @Mapping(target = "bookGenres", ignore = true)
    @InheritInverseConfiguration
    Genre toEntity(GenreDTO genreDTO);

    default List<String> mapGenreName(Set<Genre> genres) {
        return genres.stream()
                .map(Genre::getName)
                .toList();
    }
}
