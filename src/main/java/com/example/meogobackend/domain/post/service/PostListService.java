package com.example.meogobackend.domain.post.service;

import com.example.meogobackend.domain.post.domain.Post;
import com.example.meogobackend.domain.post.domain.repository.PostRepository;
import com.example.meogobackend.domain.post.presentation.dto.response.PostListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostListService {
    private final PostRepository postRepository;

    public PostListResponse findPost(String title, Pageable page) {
        Page<Post> posts;

        posts = postRepository.findAllByContentContainingOrderByIdDesc(title, page);;


        return new PostListResponse(posts.stream().map(this::ofPostResponse).collect(Collectors.toList()));
    }

    private PostListResponse.PostResponse ofPostResponse(Post post) {
        return PostListResponse.PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .nickname(post.getUser().getNickname())
                .heartCount(post.getHeartCount())
                .commentCount(post.getCommentCount())
                .createDate(post.getCreateDate())
                .build();
    }
}