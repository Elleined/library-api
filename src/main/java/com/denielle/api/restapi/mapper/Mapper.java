package com.denielle.api.restapi.mapper;

public interface Mapper<DTO, ENTITY> extends
        BaseMapper<DTO, ENTITY>,
        BeforeMapper<DTO, ENTITY>,
        AfterMapper<DTO, ENTITY> {
}
