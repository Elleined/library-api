package com.elleined.libraryapi.populator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public abstract class Populator {
    protected final ObjectMapper objectMapper;

    public Populator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public abstract void populate(final String jsonFile) throws IOException;
}