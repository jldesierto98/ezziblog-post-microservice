package com.eeezi.ezziblogrestapi.controller;


import com.eeezi.ezziblogrestapi.request.PostRequest;
import com.eeezi.ezziblogrestapi.response.PostResponse;
import com.eeezi.ezziblogrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request){
        return new ResponseEntity<>(postService.createPost(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id){
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@RequestBody PostRequest postRequest, @PathVariable Long id){
        return new ResponseEntity<>(postService.updatePost(postRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }


}
