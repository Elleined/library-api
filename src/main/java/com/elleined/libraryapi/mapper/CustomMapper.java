package com.elleined.libraryapi.mapper;

public interface CustomMapper<ENTITY, DTO> {
    DTO toDTO(ENTITY entity);
}
