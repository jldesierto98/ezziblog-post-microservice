package com.eeezi.ezziblogrestapi.comment.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CommentDto {
    private Long id;

    @NotNull(message = "Please enter your name.")
    @NotEmpty(message = "Name should not be empty!")
    private String name;

    @Email(message = "Enter a valid email")
    @NotEmpty(message = "Enter a valid email")
    private String email;

    @NotEmpty(message = "Input your comment.")
    @NotEmpty(message = "Please enter your comment.")
    private String body;

}
