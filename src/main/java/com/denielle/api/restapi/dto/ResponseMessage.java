package com.denielle.api.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
}
