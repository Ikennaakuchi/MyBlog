package com.springboot.blog.springbootblogrestapi.service.impl;

import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.entity.Posts;
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogrestapi.repository.PostRepository;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {
        Posts post = mapToEntity(postDto);
        Posts newPost = postRepository.save(post);

        return  mapToDto(newPost);
    }
    @Override
    public List<PostDto> getAllPosts() {
        List<Posts> postsList = postRepository.findAll();
        return postsList.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPost(Long postId) {
        Posts post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId ));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Posts post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Posts updatedPost = postRepository.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(Long postId) {
        Posts post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
        postRepository.delete(post);
    }

    private Posts mapToEntity(PostDto postDto){
        Posts post = new Posts();
        BeanUtils.copyProperties(postDto, post);
        return post;
    }
    private PostDto mapToDto(Posts post){
        PostDto postResponse = new PostDto();
        BeanUtils.copyProperties(post, postResponse);
        return postResponse;
    }
}
