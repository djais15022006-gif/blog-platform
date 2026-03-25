package com.blog.blogplatform.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PostRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String summary;
    private String  user;
}