package com.springboot.blog.springbootblogrestapi.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType;

}
