package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.GenreDTO;
import com.denielle.api.restapi.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/get-all-by-id")
    public List<GenreDTO> getAllById(@RequestParam("ids") List<Integer> genreIds) {
        return genreService.getAllById(genreIds);
    }

    @GetMapping("/name/{name}")
    public GenreDTO getBYName(@PathVariable("name") String genreName) {
        return genreService.getByName(genreName);
    }

    @GetMapping("/name")
    public List<String> searchByFirstLetter(@RequestParam("firstLetter") char firstLetter) {
        return genreService.searchByFirstLetter(firstLetter);
    }


    @GetMapping("/{pageNumber}/{pageSize}")
    public List<GenreDTO> getAll(@PathVariable int pageNumber,
                                 @PathVariable int pageSize) {

        return genreService.getAll(pageNumber, pageSize);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{sortDirection}/{sortProperty}")
    public List<GenreDTO> getAll(@PathVariable int pageNumber,
                                  @PathVariable int pageSize,
                                  @PathVariable String sortDirection,
                                  @PathVariable String sortProperty) {

        return genreService.getAll(pageNumber, pageSize, sortDirection, sortProperty);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestParam("name") String genreName) {
        int genreId = genreService.save(genreName);
        GenreDTO genreDTO = genreService.getById(genreId);

        return new ResponseEntity<>(genreDTO, HttpStatus.CREATED);
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<GenreDTO>> saveAll(@RequestParam("names") List<String> genreNames) {
        List<Integer> authorsId = genreService.saveAll(genreNames);
        List<GenreDTO> fetchedAuthors =  genreService.getAllById(authorsId);

        return new ResponseEntity<>(fetchedAuthors, HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable("id") int genreId,
                                           @RequestParam("name") String newGenreName) {

        genreService.update(genreId, newGenreName);

        GenreDTO fetchedgenreDTO = getById(genreId);
        return ResponseEntity.ok(fetchedgenreDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenreDTO> delete(@PathVariable("id") int genreId) {
        genreService.delete(genreId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-all-by-id")
    public ResponseEntity<GenreDTO> deleteAllById(@RequestParam("ids") List<Integer> genreIds) {
        genreService.deleteAllById(genreIds);
        return ResponseEntity.noContent().build();
    }
}
