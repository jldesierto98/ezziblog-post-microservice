package com.eeezi.ezziblogrestapi.response;

import lombok.Data;

@Data
public class PostResponse {

    private Long id;

    private String title;

    private String description;

    private String content;
}
