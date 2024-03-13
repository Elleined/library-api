package com.elleined.libraryapi.controller;

import com.elleined.libraryapi.dto.AuthorDTO;
import com.elleined.libraryapi.dto.BookDTO;
import com.elleined.libraryapi.mapper.AuthorMapper;
import com.elleined.libraryapi.mapper.BookMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.service.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    @GetMapping
    public List<AuthorDTO> getAll() {
        return authorService.getAll().stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @GetMapping("/get-all-by-id")
    public List<AuthorDTO> getAllById(@RequestParam("ids") List<Integer> authorIds) {
        return authorService.getAllById(authorIds).stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public AuthorDTO getById(@PathVariable("id") int authorId) {
        Author author = authorService.getById(authorId);
        return authorMapper.toDTO(author);
    }

    @GetMapping("/{id}/books")
    public List<BookDTO> getAllBooks(@PathVariable("id") int authorId) {
        Author author = authorService.getById(authorId);
        return authorService.getAllBooks(author).stream()
                .map(bookMapper::toDTO)
                .toList();
    }

    @GetMapping("/name")
    public List<AuthorDTO> searchByFirstLetter(@RequestParam("firstLetter") char firstLetter) {
        return authorService.searchByFirstLetter(firstLetter).stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public List<AuthorDTO> getAll(@PathVariable int pageNumber,
                                  @PathVariable int pageSize) {

        return authorService.getAll(pageNumber, pageSize).stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @GetMapping("/{pageNumber}/{pageSize}/{sortDirection}/{sortProperty}")
    public List<AuthorDTO> getAll(@PathVariable int pageNumber,
                                @PathVariable int pageSize,
                                @PathVariable String sortDirection,
                                @PathVariable String sortProperty) {

        return authorService.getAll(pageNumber, pageSize, sortDirection, sortProperty).stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @PostMapping
    public AuthorDTO save(@RequestParam("name") String name,
                          @RequestParam("biography") String biography) {

        Author author = authorService.save(name, biography);
        return authorMapper.toDTO(author);
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@PathVariable("id") int authorId,
                            @RequestParam("name") String name,
                            @RequestParam("biography") String biography) {

        Author author = authorService.getById(authorId);
        authorService.update(author, name, biography);
        return authorMapper.toDTO(author);
    }
}
