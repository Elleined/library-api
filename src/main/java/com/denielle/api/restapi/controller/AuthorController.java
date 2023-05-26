package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.service.AuthorService;
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
@RequestMapping("/api/v1/authors")
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

    @GetMapping("/name/{name}")
    public AuthorDTO getByName(@PathVariable("name") String authorName) {
        return authorService.getByName(authorName);
    }

    @GetMapping("/name")
    public List<String> searchByFirstLetter(@RequestParam("firstLetter") char firstLetter) {
        return authorService.searchByFirstLetter(firstLetter);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public List<AuthorDTO> getAll(@PathVariable int pageNumber,
                                 @PathVariable int pageSize) {

        return authorService.getAll(pageNumber, pageSize);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{sortProperty}")
    public List<AuthorDTO> getAll(@PathVariable int pageNumber,
                                  @PathVariable int pageSize,
                                  @PathVariable String sortProperty) {

        return authorService.getAll(pageNumber, pageSize, Sort.Direction.ASC, sortProperty);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> save(@RequestBody AuthorDTO authorDTO) {
        int authorId = authorService.save(authorDTO);
        AuthorDTO fetchedAuthor = authorService.getById(authorId);

        return new ResponseEntity<>(fetchedAuthor, HttpStatus.CREATED);
    }
    
    @PostMapping("/save-all")
    public ResponseEntity<List<AuthorDTO>> saveAll(@RequestBody List<AuthorDTO> authors) {
        List<Integer> authorIds = authorService.saveAll(authors);
        List<AuthorDTO> fetchedAuthors = authorService.getAllById(authorIds);

        return new ResponseEntity<>(fetchedAuthors, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable("id") int authorId,
                                            @RequestBody AuthorDTO authorDTO) {

        authorService.update(authorId, authorDTO);

        AuthorDTO fetchAuthorDTO = getById(authorId);
        return ResponseEntity.ok(fetchAuthorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDTO> delete(@PathVariable("id") int authorId) {
        authorService.delete(authorId);
        return ResponseEntity.noContent().build();
    }
}
