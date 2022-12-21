package com.springboot.blog.springbootblogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

    private String message;
    private int status;
    private String details;
}
