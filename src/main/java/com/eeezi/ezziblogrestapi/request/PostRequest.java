package com.eeezi.ezziblogrestapi.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private String title;

    private String description;

    private String content;
}

