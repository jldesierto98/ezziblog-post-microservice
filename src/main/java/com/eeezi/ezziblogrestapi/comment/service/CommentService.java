package com.eeezi.ezziblogrestapi.comment.service;

import com.eeezi.ezziblogrestapi.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {

    /**
     * Creates comment and save to Database.
     * @param postd
     * @param commentDto
     * @return
     */
    CommentDto createComment(Long postd, CommentDto commentDto);

    /**
     * Gets all the comments in a post.
     * @param postId
     * @return
     */
    List<CommentDto> getCommentByPostId(Long postId);

    /**
     * Gets the specific comment on a post
     * @param postId
     * @param commentId
     * @return
     */
    CommentDto getCommentById(Long postId, Long commentId);

    /**
     * Updates the specific comment.
     * @param postId
     * @param commentId
     * @param updateRequest
     * @return
     */
    CommentDto updateCommentById(Long postId, Long commentId, CommentDto updateRequest);

    /**
     * Deletes a comment.
     * @param postId
     * @param commentId
     */
    void deleteComment(Long postId, Long commentId);
}
