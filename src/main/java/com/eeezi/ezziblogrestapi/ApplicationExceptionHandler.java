package com.eeezi.ezziblogrestapi;

import com.eeezi.ezziblogrestapi.exception.BlogAPIException;
import com.eeezi.ezziblogrestapi.exception.ErrorResponse;
import com.eeezi.ezziblogrestapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ResourceNotFoundException ex,
                                                                 WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(Arrays.asList(ex.getMessage()), webRequest.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<Object> handleBlogApiException(BlogAPIException ex,
                                                         WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(Arrays.asList(ex.getMessage()), webRequest.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex,
                                                        WebRequest webRequest){
        ErrorResponse response = new ErrorResponse(Arrays.asList(ex.getMessage()), webRequest.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
