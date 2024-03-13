package com.elleined.libraryapi.controller;

import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.dto.GenreDTO;
import com.elleined.libraryapi.mapper.BookMapper;
import com.elleined.libraryapi.mapper.GenreMapper;
import com.elleined.libraryapi.model.Genre;
import com.elleined.libraryapi.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    private final GenreMapper genreMapper;
    private final BookMapper bookMapper;

    @GetMapping
    public List<GenreDTO> getAll() {
        return genreService.getAll().stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public GenreDTO getById(@PathVariable("id") int genreId) {
        Genre genre = genreService.getById(genreId);
        return genreMapper.toDTO(genre);
    }

    @GetMapping("/get-all-books-by-genre/{genreId}")
    public List<BookDTO> getAllByGenre(@PathVariable("genreId") int genreId) {
        Genre genre = genreService.getById(genreId);
        return genreService.getAllByGenre(genre).stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @GetMapping("/get-all-by-id")
    public List<GenreDTO> getAllById(@RequestParam("ids") List<Integer> genreIds) {
        return genreService.getAllById(genreIds).stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @GetMapping("/name")
    public List<GenreDTO> searchByFirstLetter(@RequestParam("firstLetter") char firstLetter) {
        return genreService.searchByFirstLetter(firstLetter).stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public List<GenreDTO> getAll(@PathVariable int pageNumber,
                                 @PathVariable int pageSize) {

        return genreService.getAll(pageNumber, pageSize).stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @GetMapping("/{pageNumber}/{pageSize}/{sortDirection}/{sortProperty}")
    public List<GenreDTO> getAll(@PathVariable int pageNumber,
                                  @PathVariable int pageSize,
                                  @PathVariable String sortDirection,
                                  @PathVariable String sortProperty) {

        return genreService.getAll(pageNumber, pageSize, sortDirection, sortProperty).stream()
                .map(genreMapper::toDTO)
                .toList();
    }

    @PostMapping
    public GenreDTO save(@RequestParam("genreName") String genreName) {
        Genre genre = genreService.save(genreName);
        return genreMapper.toDTO(genre);
    }

    @PatchMapping("/{id}")
    public GenreDTO update(@PathVariable("id") int genreId,
                           @RequestParam("name") String newGenreName) {

        Genre genre = genreService.getById(genreId);
        genreService.update(genre, newGenreName);
        return genreMapper.toDTO(genre);
    }
}
