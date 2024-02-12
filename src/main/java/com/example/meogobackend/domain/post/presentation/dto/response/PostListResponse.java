package com.example.meogobackend.domain.post.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PostListResponse {
    private final List<PostResponse> postResponses;

    @Getter
    @Builder
    public static class PostResponse {
        private final Long id;
        private final String nickname;
        private final String content;
        private final Integer heartCount;
        private final Integer commentCount;
        private final String createDate;
    }
}