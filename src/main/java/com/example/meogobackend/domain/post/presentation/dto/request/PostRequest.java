package com.example.meogobackend.domain.post.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class PostRequest {
    @NotBlank
    @Size(min = 1, max = 100)
    private String content;
}
