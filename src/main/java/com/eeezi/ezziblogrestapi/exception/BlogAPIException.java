package com.eeezi.ezziblogrestapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BlogAPIException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;

    public BlogAPIException(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public BlogAPIException(HttpStatus httpStatus, String message, String message1){
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }


}
