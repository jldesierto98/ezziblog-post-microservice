package com.eeezi.ezziblogrestapi.comment.service;

import com.eeezi.ezziblogrestapi.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postd, CommentDto commentDto);

    List<CommentDto> getCommentByPostId(Long postId);

    CommentDto getCommentById(Long postId, Long commentId);
}
