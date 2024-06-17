package com.eeezi.ezziblogrestapi.post.service;


import com.eeezi.ezziblogrestapi.category.dto.CategoryDto;
import com.eeezi.ezziblogrestapi.category.entity.Category;
import com.eeezi.ezziblogrestapi.category.service.CategoryService;
import com.eeezi.ezziblogrestapi.post.entity.Post;
import com.eeezi.ezziblogrestapi.exception.ResourceNotFoundException;
import com.eeezi.ezziblogrestapi.post.repository.PostRepository;
import com.eeezi.ezziblogrestapi.post.request.PostRequest;
import com.eeezi.ezziblogrestapi.post.response.PostPageResponse;
import com.eeezi.ezziblogrestapi.post.response.PostResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;


    @Override
    @Transactional
    public PostResponse createPost(PostRequest postRequest) {
        //TODO : refactor code, use modelMapper instead of building the Post entity.
        Post postBuild = new Post();
        postBuild.setTitle(postRequest.getTitle());
        postBuild.setDescription(postRequest.getDescription());
        postBuild.setContent(postRequest.getContent());

        // Get category in DB.
        CategoryDto categoryDto = categoryService.getCategoryById(postRequest.getCategoryId());

        Category category = modelMapper.map(categoryDto, Category.class);

        // Set category
        postBuild.setCategory(category);

        Post savedPost = postRepository.save(postBuild);

        PostResponse response = new PostResponse();
        response.setId(savedPost.getId());
        response.setTitle(savedPost.getTitle());
        response.setDescription(savedPost.getDescription());
        response.setContent(savedPost.getContent());

        log.info("====___ Post Created {}____===== Title : {} ===== ID : {}", LocalDateTime.now(), response.getTitle(), response.getId());
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
    public PostPageResponse getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<PostResponse> content =  posts.stream()
                .map(this::postResponseMapper)
                .collect(Collectors.toList());

        PostPageResponse postPageResponse = new PostPageResponse();
        postPageResponse.setContent(content);
        postPageResponse.setPageNo(posts.getNumber());
        postPageResponse.setPageSize(posts.getSize());
        postPageResponse.setTotalElement(posts.getTotalElements());
        postPageResponse.setTotalPages(posts.getTotalPages());
        postPageResponse.setLast(posts.isLast());

        return postPageResponse;

    }

    @Override
    public PostResponse updatePost(PostRequest postRequest, Long id) {

        // Get the required Post in the DB using the provided ID.
        // If a Post with provided ID is not found, throw ResourceNotFoundException.
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setDescription(postRequest.getDescription());

        // Save the updated Post.
        Post updatedPost =  postRepository.save(post);

        return this.postResponseMapper(updatedPost);
    }

    @Override
    public String deletePost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

        postRepository.deleteById(id);


        return "Post entity deleted successfully.";
    }

    /**
     * Maps the Post Object into a PostResponse Object. This helper method acts as a converter Post -> PostResponse.
     *
     * @param post
     * @return PostResponse
     */
    private PostResponse postResponseMapper(Post post){
        return modelMapper.map(post, PostResponse.class);
    }

    @Override
    public ByteArrayInputStream exportToExcel() {
        List<Post> components = postRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Post Export Sheet");

            // Header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Description");
            headerRow.createCell(1).setCellValue("Content");
            headerRow.createCell(2).setCellValue("Title");

            // Data rows
            int rowIdx = 1;
            for (Post post : components) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(post.getDescription());
                row.createCell(1).setCellValue(post.getContent());
                row.createCell(2).setCellValue(post.getTitle());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Excel file", e);
        }
    }


    @Override
    public List<PostResponse> getPostsByCategory(Long categoryId) {
        List<Post> fetchedPosts = postRepository.findByCategoryId(categoryId);

        List<PostResponse> mappedPosts = fetchedPosts.stream()
                .map(post -> modelMapper.map(post, PostResponse.class))
                .collect(Collectors.toList());

        return mappedPosts;
    }
}

