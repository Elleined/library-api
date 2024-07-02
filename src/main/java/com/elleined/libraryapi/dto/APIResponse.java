package com.elleined.libraryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class APIResponse {
    private final HttpStatus status;
    private final String message;
    private LocalDateTime timestamp;

    public APIResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
