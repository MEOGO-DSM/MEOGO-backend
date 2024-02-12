package com.example.meogobackend.domain.comment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponse {
    private final Long id;
    private final String nickname;
    private final String content;
    private final String createDate;

    @Builder
    public CommentResponse(Long id, String nickname, String content, String createDate) {
        this.id = id;
        this.nickname = nickname;
        this.content = content;
        this.createDate = createDate;
    }
}
