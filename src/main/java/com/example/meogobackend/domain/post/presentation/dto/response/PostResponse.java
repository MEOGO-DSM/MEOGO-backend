package com.example.meogobackend.domain.post.presentation.dto.response;

import com.example.meogobackend.domain.comment.presentation.dto.response.CommentResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponse {
    private final Long id;
    private final String nickname;
    private final String content;
    private final Integer heartCount;
    private final Integer commentCount;
    private final List<CommentResponse> comments;
    private final String createDate;

    @Builder
    private PostResponse(Long id, String nickname, String content, Integer heartCount, String createDate, Integer commentCount, List<CommentResponse> comments){
        this.id = id;
        this.nickname = nickname;
        this.content = content;
        this.heartCount = heartCount;
        this.commentCount = commentCount;
        this.createDate = createDate;
        this.comments = comments;
    }
}
