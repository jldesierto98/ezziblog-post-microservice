package com.eeezi.ezziblogrestapi.post.controller;


import com.eeezi.ezziblogrestapi.post.request.PostRequest;
import com.eeezi.ezziblogrestapi.post.response.PostPageResponse;
import com.eeezi.ezziblogrestapi.post.response.PostResponse;
import com.eeezi.ezziblogrestapi.post.service.PostService;
import com.eeezi.ezziblogrestapi.post.utils.AppConstant;
import com.eeezi.ezziblogrestapi.users.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/api/posts")
@RestController
@AllArgsConstructor
@Tag(
        name = "Rest APIs for Post Resources"
)
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to record POST into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 - CREATED"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request){
        return new ResponseEntity<>(postService.createPost(request), HttpStatus.CREATED);
    }


    @Operation(
            summary = "GET Post REST API",
            description = "Gets a Post REST API by its ID"
    )
    @ApiResponse(
            responseCode = "302",
            description = "Http Status 302 - FOUND"
    )
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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@RequestBody PostRequest postRequest, @PathVariable Long id){
        return new ResponseEntity<>(postService.updatePost(postRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportPost(){
        ByteArrayInputStream excelFile = postService.exportToExcel();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=post_exports.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelFile.readAllBytes());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostResponse>> getPostsByCategory(@PathVariable Long categoryId){
        return new ResponseEntity<>(postService.getPostsByCategory(categoryId), HttpStatus.OK);
    }


}
