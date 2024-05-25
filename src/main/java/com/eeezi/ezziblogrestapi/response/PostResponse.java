package com.eeezi.ezziblogrestapi.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PostResponse {

    private Long id;

    private String title;

    private String description;

    private String content;
}
