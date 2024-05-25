package com.eeezi.ezziblogrestapi.service;


import com.eeezi.ezziblogrestapi.entity.Post;
import com.eeezi.ezziblogrestapi.exception.ResourceNotFoundException;
import com.eeezi.ezziblogrestapi.repository.PostRepository;
import com.eeezi.ezziblogrestapi.request.PostRequest;
import com.eeezi.ezziblogrestapi.response.PostResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;


    @Override
    @Transactional
    public PostResponse createPost(PostRequest post) {

        Post postBuild = new Post();
        postBuild.setTitle(post.getTitle());
        postBuild.setDescription(post.getDescription());
        postBuild.setContent(post.getContent());

        Post savedPost = postRepository.save(postBuild);

        PostResponse response = new PostResponse();
        response.setId(savedPost.getId());
        response.setTitle(savedPost.getTitle());
        response.setDescription(savedPost.getDescription());
        response.setContent(savedPost.getContent());

        log.info("====___ Post Created {}____===== Titlle : {}", LocalDateTime.now(), response.getTitle());
        return response;
    }

    @Override
    public PostResponse getPost(Long id) {

        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            return this.postResponseMapper(optionalPost.get());
        }else {
            throw new ResourceNotFoundException("Post", "id", id.toString());
        }
    }

    @Override
    public List<PostResponse> getAllPosts() {

        List<Post> postList = postRepository.findAll();

       return postList.stream()
                .map(this::postResponseMapper)
                .collect(Collectors.toList());

    }

    /**
     * Maps the Post Object into a PostResponse Object. This helper method acts as a converter Post -> PostResponse.
     *
     * @param post
     * @return PostResponse
     */
    private PostResponse postResponseMapper(Post post){
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setContent(post.getContent());
        postResponse.setDescription(post.getContent());
        postResponse.setTitle(post.getTitle());

        return postResponse;
    }
}
