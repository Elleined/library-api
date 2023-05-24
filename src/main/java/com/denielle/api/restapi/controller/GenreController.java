package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.GenreDTO;
import com.denielle.api.restapi.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDTO> getAll() {
        return genreService.getAll();
    }

    @GetMapping("/{id}")
    public GenreDTO getById(@PathVariable("id") int genreId) {
        return genreService.getById(genreId);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public List<GenreDTO> getAll(@PathVariable int pageNumber,
                                 @PathVariable int pageSize) {

        return genreService.getAll(pageNumber, pageSize);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{sortProperty}")
    public List<GenreDTO> getAll(@PathVariable int pageNumber,
                                 @PathVariable int pageSize,
                                 @PathVariable String sortProperty) {

        return genreService.getAll(pageNumber, pageSize, Sort.Direction.ASC, sortProperty);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestParam("name") String genreName) {
        int genreId = genreService.save(genreName);
        GenreDTO genreDTO = genreService.getById(genreId);

        return new ResponseEntity<>(genreDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable("id") int genreId,
                                           @RequestParam("name") String newGenreName) {

        genreService.update(genreId, newGenreName);
        GenreDTO genreDTO = getById(genreId);

        return new ResponseEntity<>(genreDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenreDTO> delete(@PathVariable("id") int genreId) {
        genreService.delete(genreId);
        return ResponseEntity.noContent().build();
    }
}
