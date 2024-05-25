package com.eeezi.ezziblogrestapi;

import com.eeezi.ezziblogrestapi.exception.ErrorResponse;
import com.eeezi.ezziblogrestapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ResourceNotFoundException ex){
        ErrorResponse response = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
