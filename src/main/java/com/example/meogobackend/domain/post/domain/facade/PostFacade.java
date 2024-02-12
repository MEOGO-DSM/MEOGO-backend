package com.example.meogobackend.domain.post.domain.facade;

import com.example.meogobackend.domain.post.domain.Post;
import com.example.meogobackend.domain.post.domain.repository.PostRepository;
import com.example.meogobackend.domain.post.service.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostFacade {
    private final PostRepository postRepository;

    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
    }
}
