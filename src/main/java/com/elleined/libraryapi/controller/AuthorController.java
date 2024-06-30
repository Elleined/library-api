package com.elleined.libraryapi.controller;

import com.elleined.libraryapi.dto.AuthorDTO;
import com.elleined.libraryapi.mapper.AuthorMapper;
import com.elleined.libraryapi.mapper.BookMapper;
import com.elleined.libraryapi.model.Author;
import com.elleined.libraryapi.service.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    @GetMapping("/{id}")
    public AuthorDTO getById(@PathVariable("id") int id) {
        Author author = authorService.getById(id);
        return authorMapper.toDTO(author);
    }

    @PostMapping
    public AuthorDTO save(@RequestParam("name") String name,
                          @RequestParam("biography") String biography) {

        Author author = authorService.save(name, biography);
        return authorMapper.toDTO(author);
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@PathVariable("id") int id,
                            @RequestParam("name") String name,
                            @RequestParam("biography") String biography) {

        Author author = authorService.getById(id);
        authorService.update(author, name, biography);
        return authorMapper.toDTO(author);
    }

    @GetMapping
    public Page<AuthorDTO> getAll(@RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                  @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                  @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                  @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return authorService.getAll(pageable).map(authorMapper::toDTO);
    }

    @GetMapping("/search")
    public Page<AuthorDTO> getAllByFirstLetter(@RequestParam("firstLetter") char firstLetter,
                                               @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                               @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                               @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                               @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return authorService.getAllByFirstLetter(firstLetter, pageable).map(authorMapper::toDTO);
    }
}
