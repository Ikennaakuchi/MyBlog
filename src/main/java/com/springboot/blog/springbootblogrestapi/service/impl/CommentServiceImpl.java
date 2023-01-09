package com.springboot.blog.springbootblogrestapi.service.impl;

import com.springboot.blog.springbootblogrestapi.dto.CommentDto;
import com.springboot.blog.springbootblogrestapi.entity.Comment;
import com.springboot.blog.springbootblogrestapi.entity.Post;
import com.springboot.blog.springbootblogrestapi.exception.APIException;
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogrestapi.repository.CommentRepository;
import com.springboot.blog.springbootblogrestapi.repository.PostRepository;
import com.springboot.blog.springbootblogrestapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper mapper;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
        return mapToDto(comment);
    }

    @Override
    public List<CommentDto> getAllComments(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPostId(postId);
        return commentList.stream().map(this::mapToDto).collect(Collectors.toList());

//        List<CommentDto> commentDtoList = new ArrayList<>();
//        for (Comment comment: commentList){
//           CommentDto commentDto = mapToDto(comment);
//            commentDtoList.add(commentDto);
//        }
//        return commentDtoList;
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Comment comment = getComment(postId, commentId);
        return mapToDto(comment);
//        Comment comment = commentRepository.findCommentByIdAndPostId(commentId, postId);
//        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long commentId, Long postId, CommentDto commentDto) {
        Comment comment = getComment(postId, commentId);

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setCommentBody(commentDto.getCommentBody());

        return mapToDto(commentRepository.save(comment));
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Comment comment = getComment(postId, commentId);
        commentRepository.delete(comment);
    }

    private Comment getComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Comment or post does not exist");
        }
        return comment;
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto,Comment.class);
        return comment;
    }
}
