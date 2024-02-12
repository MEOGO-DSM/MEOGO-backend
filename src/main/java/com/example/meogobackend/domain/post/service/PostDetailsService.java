package com.example.meogobackend.domain.post.service;

import com.example.meogobackend.domain.comment.presentation.dto.response.CommentResponse;
import com.example.meogobackend.domain.post.domain.Post;
import com.example.meogobackend.domain.post.domain.repository.PostRepository;
import com.example.meogobackend.domain.post.presentation.dto.response.PostResponse;
import com.example.meogobackend.domain.post.service.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostDetailsService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostResponse getPostDetails(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);

        return PostResponse.builder()
                .id(post.getId())
                .nickname(post.getUser().getNickname())
                .content(post.getContent())
                .heartCount(post.getHeartCount())
                .commentCount(post.getCommentCount())
                .createDate(post.getCreateDate())
                .comments(post.getComments().stream().map(comment -> {
                            return CommentResponse.builder()
                                    .id(comment.getId())
                                    .nickname(comment.getUser().getNickname())
                                    .content(comment.getContent())
                                    .createDate(comment.getCreateDate())
                                    .build();
                        }
                ).collect(Collectors.toList()))
                .build();
    }
}