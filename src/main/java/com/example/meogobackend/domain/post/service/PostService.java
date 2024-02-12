package com.example.meogobackend.domain.post.service;

import com.example.meogobackend.domain.post.domain.Post;
import com.example.meogobackend.domain.post.domain.repository.PostRepository;
import com.example.meogobackend.domain.post.presentation.dto.request.PostRequest;
import com.example.meogobackend.domain.post.presentation.dto.response.ReturnIdResponse;
import com.example.meogobackend.domain.post.service.exception.PostNotFoundException;
import com.example.meogobackend.domain.post.service.exception.UserNotMatchException;
import com.example.meogobackend.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Transactional
    public ReturnIdResponse create(PostRequest request) {
        Post post = postRepository.save(Post.builder()
                .content(request.getContent())
                .user(userUtil.getUser())
                .build());

        return new ReturnIdResponse(post.getId());
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if (!post.getUser().getAccountId().equals(userUtil.getAccountId())) throw UserNotMatchException.EXCEPTION;
        postRepository.delete(post);
    }

    @Transactional
    public Long update(Long id, PostRequest request) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if(!post.getUser().getAccountId().equals(userUtil.getAccountId())) throw UserNotMatchException.EXCEPTION;

        return post.update(request.getContent());
    }
}
