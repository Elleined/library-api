package com.denielle.api.restapi.mapper;

import com.denielle.api.restapi.dto.GenreDTO;
import com.denielle.api.restapi.model.Genre;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toDTO(Genre genre);

    default List<String> mapGenreName(Set<Genre> genres) {
        if (genres == null) return null;
        return genres.stream()
                .map(Genre::getName)
                .toList();
    }
}
