package com.elleined.libraryapi.controller;

import com.elleined.libraryapi.dto.GenreDTO;
import com.elleined.libraryapi.mapper.GenreMapper;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @GetMapping("/{id}")
    public GenreDTO getById(@PathVariable("id") int id,
                            @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Genre genre = genreService.getById(id);

        return genreMapper.toDTO(genre).addLinks(includeRelatedLinks);
    }

    @GetMapping
    public Page<GenreDTO> getAll(@RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                 @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                 @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                 @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                 @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);

        return genreService.getAll(pageable)
                .map(genreMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }

    @PostMapping
    public GenreDTO save(@RequestParam("name") String name,
                         @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Genre genre = genreService.save(name);

        return genreMapper.toDTO(genre).addLinks(includeRelatedLinks);
    }

    @PatchMapping("/{id}")
    public GenreDTO update(@PathVariable("id") int genreId,
                           @RequestParam("name") String name,
                           @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Genre genre = genreService.getById(genreId);
        genreService.update(genre, name);

        return genreMapper.toDTO(genre).addLinks(includeRelatedLinks);
    }

    @GetMapping("/search")
    public Page<GenreDTO> getAllByFirstLetter(@RequestParam("firstLetter") char firstLetter,
                                              @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                              @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                              @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                              @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                              @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);

        return genreService.getAllByNameFirstLetter(firstLetter, pageable)
                .map(genreMapper::toDTO)
                .map(dto -> dto.addLinks(includeRelatedLinks));
    }
}
