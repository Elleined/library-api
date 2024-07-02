package com.elleined.libraryapi.mapper;

import com.elleined.libraryapi.model.PrimaryKeyIdentity;

public interface CustomMapper<ENTITY extends PrimaryKeyIdentity,
        DTO extends com.elleined.libraryapi.dto.DTO> {
    DTO toDTO(ENTITY entity);
}
