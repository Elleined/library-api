package com.denielle.api.restapi.mapper;

public interface AfterMapper<DTO, ENTITY> {
    void toEntityAfterMapping(DTO dto);
    void toDTOAfterMapping(ENTITY entity);
}
