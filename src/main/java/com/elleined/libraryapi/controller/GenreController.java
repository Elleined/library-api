package com.elleined.bookauthorgenreapi.controller;

import com.elleined.bookauthorgenreapi.dto.GenreDTO;
import com.elleined.bookauthorgenreapi.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/genres")
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
    public GenreDTO save(@RequestBody GenreDTO genreName) {
        return genreService.save(genreName);
    }

    @PostMapping("/save-all")
    public List<GenreDTO> saveAll(@RequestBody List<GenreDTO> genreNames) {
        return genreService.saveAll(genreNames);
    }
    


    @PatchMapping("/{id}")
    public GenreDTO update(@PathVariable("id") int genreId,
                           @RequestParam("name") String newGenreName) {

        genreService.update(genreId, newGenreName);
        return genreService.getById(genreId);
    }
}
