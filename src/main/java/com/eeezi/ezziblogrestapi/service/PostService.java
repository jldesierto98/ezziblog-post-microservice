package com.eeezi.ezziblogrestapi.service;


import com.eeezi.ezziblogrestapi.request.PostRequest;
import com.eeezi.ezziblogrestapi.response.PostPageResponse;
import com.eeezi.ezziblogrestapi.response.PostResponse;

import java.util.List;

public interface PostService {
     /**
      * Creates a Post from the Client and saves to DB.
      *
      * @param post
      * @return
      */
     PostResponse createPost(PostRequest post);

     /**
      * Gets a Post with the specific ID.
      *
      * @param id
      * @return PostResponse
      */
     PostResponse getPost(Long id);

     /**
      * Gets all Post saved in the DB.
      *
      * @return List<PostResponse>
      */
     PostPageResponse getAllPosts(Integer pageNo, Integer pagSize, String sortBy, String sortDir);

     /**
      * Updates the current existing post.
      *
      * @param postRequest
      * @param id
      * @return PostResponse
      */
     PostResponse updatePost(PostRequest postRequest, Long id);

     /**
      * Deletes a Post by ID.
      *
      * @param id
      * @return String
      */
     String deletePost(Long id);
}
