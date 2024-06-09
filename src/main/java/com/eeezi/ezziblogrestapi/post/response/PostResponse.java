package com.eeezi.ezziblogrestapi.post.response;

import com.eeezi.ezziblogrestapi.comment.dto.CommentDto;
import lombok.Data;

import java.util.Set;

@Data
public class PostResponse {

    private Long id;

    private String title;

    private String description;

    private String content;

    private Set<CommentDto> comments;
}
