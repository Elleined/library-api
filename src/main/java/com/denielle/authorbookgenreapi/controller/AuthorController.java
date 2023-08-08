package com.denielle.authorbookgenreapi.controller;

import com.denielle.authorbookgenreapi.dto.AuthorDTO;
import com.denielle.authorbookgenreapi.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorDTO> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/get-all-by-id")
    public List<AuthorDTO> getAllById(@RequestParam("ids") List<Integer> authorIds) {
        return authorService.getAllById(authorIds);
    }

    @GetMapping("/{id}")
    public AuthorDTO getById(@PathVariable("id") int authorId) {
        return authorService.getById(authorId);
    }

    @GetMapping("/{id}/books")
    public List<String> getAllBooks(@PathVariable("id") int authorId) {
        return authorService.getAllBooks(authorId);
    }

    @GetMapping("/{id}/books/count")
    public int getBookCount(@PathVariable("id") int authorId) {
        return authorService.getBookCount(authorId);
    }

    @GetMapping("/name")
    public List<String> searchByFirstLetter(@RequestParam("firstLetter") char firstLetter) {
        return authorService.searchByFirstLetter(firstLetter);
    }

    @GetMapping("/name/{name}")
    public AuthorDTO getByName(@PathVariable("name") String authorName) {
        return authorService.getByName(authorName);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public List<AuthorDTO> getAll(@PathVariable int pageNumber,
                                  @PathVariable int pageSize) {

        return authorService.getAll(pageNumber, pageSize);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{sortDirection}/{sortProperty}")
    public List<AuthorDTO> getAll(@PathVariable int pageNumber,
                                @PathVariable int pageSize,
                                @PathVariable String sortDirection,
                                @PathVariable String sortProperty) {

        return authorService.getAll(pageNumber, pageSize, sortDirection, sortProperty);
    }

    @PostMapping
    public AuthorDTO save(@Valid @RequestBody AuthorDTO authorDTO) {
        return authorService.save(authorDTO);
    }

    @PostMapping("/save-all")
    public List<AuthorDTO> saveAll (@RequestBody List<AuthorDTO> authors) {
        return authorService.saveAll(authors);
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@PathVariable("id") int authorId,
                            @Valid @RequestBody AuthorDTO authorDTO) {

        authorService.update(authorId, authorDTO);
        return authorService.getById(authorId);
    }
}
