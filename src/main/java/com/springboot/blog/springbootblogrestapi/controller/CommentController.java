package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.dto.CommentDto;
import com.springboot.blog.springbootblogrestapi.service.CommentService;
import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable Long postId){
        return new ResponseEntity<>(commentService.getAllComments(postId), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId, @PathVariable Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long postId,
                                                    @PathVariable Long commentId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateComment(commentId, postId, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
