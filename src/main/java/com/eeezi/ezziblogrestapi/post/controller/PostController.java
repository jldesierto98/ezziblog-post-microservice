package com.eeezi.ezziblogrestapi.post.controller;


import com.eeezi.ezziblogrestapi.post.request.PostRequest;
import com.eeezi.ezziblogrestapi.post.response.PostPageResponse;
import com.eeezi.ezziblogrestapi.post.response.PostResponse;
import com.eeezi.ezziblogrestapi.post.service.PostService;
import com.eeezi.ezziblogrestapi.post.utils.AppConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/posts")
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
    public ResponseEntity<PostPageResponse> getAllPosts(@RequestParam(value = "pageNo", defaultValue = AppConstant.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
                                                        @RequestParam(value = "pageSize", defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
                                                        @RequestParam(value = "sortBy", defaultValue = AppConstant.DEFAULT_SORT_BY, required = false) String sortBy,
                                                        @RequestParam(value = "sortDir", defaultValue = AppConstant.DEFAULT_SORT_DIRECTION, required = false) String sortDir){

        return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.FOUND);
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
