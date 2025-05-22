package com.robyonrails.gftinditexexercise.infraestructure.adapter.input.rest.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Dto para los mensajes de error
 *
 * @author Robert Lungu
 */
@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

}

