package com.denielle.api.restapi.controller;

import com.denielle.api.restapi.dto.AuthorDTO;
import com.denielle.api.restapi.dto.ResponseMessage;
import com.denielle.api.restapi.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/authors")
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
    public ResponseEntity<?> save(@Valid @RequestBody AuthorDTO authorDTO,
                                  BindingResult result) {

        if (result.hasErrors()) {
            List<ResponseMessage> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .map(errorMessage -> new ResponseMessage(HttpStatus.BAD_REQUEST, errorMessage))
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }

        int authorId = authorService.save(authorDTO);
        AuthorDTO fetchedAuthor = authorService.getById(authorId);

        return new ResponseEntity<>(fetchedAuthor, HttpStatus.CREATED);
    }

    @PostMapping("/save-all")
    public ResponseEntity<?> saveAll (@RequestBody List<AuthorDTO> authors) {
        List<Integer> authorIds = authorService.saveAll(authors);
        List<AuthorDTO> fetchedAuthors = authorService.getAllById(authorIds);

        return new ResponseEntity<>(fetchedAuthors, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int authorId,
                                    @Valid @RequestBody AuthorDTO authorDTO,
                                    BindingResult result) {

        if (result.hasErrors()) {
            List<ResponseMessage> errors = result.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .map(errorMessage -> new ResponseMessage(HttpStatus.BAD_REQUEST, errorMessage))
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
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
