package com.eeezi.ezziblogrestapi.comment.controller;

import com.eeezi.ezziblogrestapi.comment.dto.CommentDto;
import com.eeezi.ezziblogrestapi.comment.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable Long postId){
        return new ResponseEntity<>(commentService.getCommentByPostId(postId), HttpStatus.FOUND);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId, @PathVariable Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.FOUND);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentDto updateRequest){
        return new ResponseEntity<>(commentService.updateCommentById(postId, commentId, updateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment Deleted Successfully", HttpStatus.OK);
    }

}
