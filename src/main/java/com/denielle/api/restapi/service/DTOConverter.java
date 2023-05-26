package com.denielle.api.restapi.service;

import org.springframework.stereotype.Service;

@Service
@FunctionalInterface
public interface DTOConverter<T, R> {
    R convertToDTO(T t);
}
