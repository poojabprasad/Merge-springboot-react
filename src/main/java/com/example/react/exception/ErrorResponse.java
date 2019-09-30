package com.example.react.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private HttpStatus httpStatus;

    public static ErrorResponse of(String message, HttpStatus httpStatus) {
        return new ErrorResponse(message, httpStatus);
    }
}
