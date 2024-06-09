package com.eeezi.ezziblogrestapi.comment.service;

import com.eeezi.ezziblogrestapi.comment.dto.CommentDto;
import com.eeezi.ezziblogrestapi.comment.entity.Comment;
import com.eeezi.ezziblogrestapi.comment.repository.CommentRepository;
import com.eeezi.ezziblogrestapi.exception.BlogAPIException;
import com.eeezi.ezziblogrestapi.post.entity.Post;
import com.eeezi.ezziblogrestapi.exception.ResourceNotFoundException;
import com.eeezi.ezziblogrestapi.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId.toString()));

        Comment mappedComment = this.mapToEntity(commentDto);
        mappedComment.setPost(post);

        Comment newComment = commentRepository.save(mappedComment);

        return this.mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(Long postId) {
        List<Comment> comment = commentRepository.findByPostId(postId);
        return this.mapToListCommentDto(comment);
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Comment comment = getCommentAndPost(postId, commentId);

        return this.mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(Long postId, Long commentId, CommentDto updateRequest) {

        Comment comment = getCommentAndPost(postId, commentId);

        comment.setBody(updateRequest.getBody());
        comment.setEmail(updateRequest.getEmail());
        comment.setName(updateRequest.getName());

        return this.mapToDto(commentRepository.save(comment));
    }


    @Override
    public void deleteComment(Long postId, Long commentId) {

        getCommentAndPost(postId, commentId);

        commentRepository.deleteById(commentId);
    }

    private CommentDto mapToDto(Comment comment) {
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setName(comment.getName());
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto){
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
//        comment.setName(commentDto.getName());
        return modelMapper.map(commentDto, Comment.class);
    }

    /**
     * Transforms List of Comment to List of CommentDto
     * @param comments
     * @return List<CommentDto>
     */
    private List<CommentDto> mapToListCommentDto(List<Comment> comments){
        return comments.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Check the database if postId and commentId exists.
     *
     * Checks if commentId belongs to the correct postId.
     * @param postId
     * @param commentId
     * @return Comment
     */
    private Comment getCommentAndPost(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", commentId.toString()));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId.toString()));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return comment;
    }
}
