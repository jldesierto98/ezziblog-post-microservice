package com.eeezi.ezziblogrestapi.post.response;

import lombok.Data;

import java.util.List;

@Data
public class PostPageResponse {

    private List<PostResponse> content;

    private Integer pageNo;

    private Integer pageSize;

    private Long totalElement;

    private Integer totalPages;

    private Boolean last;

}
