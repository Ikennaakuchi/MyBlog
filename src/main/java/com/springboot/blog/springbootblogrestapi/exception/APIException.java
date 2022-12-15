package com.springboot.blog.springbootblogrestapi.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public APIException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public APIException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
