package com.denielle.api.restapi.service;

import com.denielle.api.restapi.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;




}
