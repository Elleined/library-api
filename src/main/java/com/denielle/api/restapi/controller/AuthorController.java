package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/{id}")
    public AuthorDTO getById(@PathVariable("id") int genreId) {
        return authorService.getById(genreId);
    }

    @GetMapping("/name/{name}")
    public AuthorDTO getByName(@PathVariable("name") String genreName) {
        return authorService.getByName(genreName);
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
    public ResponseEntity<AuthorDTO> save(AuthorDTO authorDTO) {
        int authorId = authorService.save(authorDTO);

        AuthorDTO fetchAuthor = authorService.getById(authorId);
        authorDTO.setCreatedAt(LocalDateTime.now());

        return new ResponseEntity<>(fetchAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable("id") int authorId,
                                           @RequestBody AuthorDTO authorDTO) {

        authorService.update(authorId, authorDTO);

        AuthorDTO fetchAuthorDTO = getById(authorId);
        fetchAuthorDTO.setUpdatedAt(LocalDateTime.now());

        return new ResponseEntity<>(fetchAuthorDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDTO> delete(@PathVariable("id") int authorId) {
        authorService.delete(authorId);
        return ResponseEntity.noContent().build();
    }
}
