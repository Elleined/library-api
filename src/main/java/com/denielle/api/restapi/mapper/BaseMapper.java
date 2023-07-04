package com.denielle.api.restapi.mapper;

import java.util.List;
 public interface BaseMapper<DTO, ENTITY> {

    DTO toDTO(ENTITY entity);
    ENTITY toEntity(DTO dto);
    List<DTO> toDTOList(List<ENTITY> entities);
    List<ENTITY> toEntityList(List<DTO> dtos);
}
