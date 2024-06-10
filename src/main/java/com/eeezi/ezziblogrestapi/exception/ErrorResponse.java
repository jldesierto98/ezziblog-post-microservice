package com.eeezi.ezziblogrestapi.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private List<String> message;
    String details;

    public ErrorResponse(List<String> message, String details) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }
}
