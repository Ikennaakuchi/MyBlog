package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.dto.SignUpDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    void signUp(SignUpDto signUpDto);
}
