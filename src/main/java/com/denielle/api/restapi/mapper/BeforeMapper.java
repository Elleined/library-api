package com.denielle.api.restapi.mapper;

public interface BeforeMapper<DTO, ENTITY> {
    void toEntityBeforeMapping(DTO dto);

    void toDTOBeforeMapping(ENTITY entity);
}
