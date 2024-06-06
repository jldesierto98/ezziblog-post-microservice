package com.eeezi.ezziblogrestapi.post.response;

import lombok.Data;

@Data
public class PostResponse {

    private Long id;

    private String title;

    private String description;

    private String content;
}
