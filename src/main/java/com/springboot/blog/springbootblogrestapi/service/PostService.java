package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String SortBy, String sortDir);
    PostDto getPost(Long postId);
    PostDto updatePost(PostDto postDto, Long postId);
    void deletePost(Long postId);
}
