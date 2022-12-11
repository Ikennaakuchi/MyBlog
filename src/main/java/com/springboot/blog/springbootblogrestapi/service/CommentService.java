package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);
    List<CommentDto> getAllComments(Long postId);
    CommentDto getCommentById(Long postId, Long commentId);
    CommentDto updateComment(Long commentId, Long postId, CommentDto commentDto);
    void deleteComment(Long postId, Long commentId);
}
