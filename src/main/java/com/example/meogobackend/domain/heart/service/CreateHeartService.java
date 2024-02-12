package com.example.meogobackend.domain.heart.service;

import com.example.meogobackend.domain.heart.domain.Heart;
import com.example.meogobackend.domain.heart.domain.facade.HeartFacade;
import com.example.meogobackend.domain.heart.domain.repository.HeartRepository;
import com.example.meogobackend.domain.heart.presentation.dto.HeartResponse;
import com.example.meogobackend.domain.heart.service.exception.HeartExistException;
import com.example.meogobackend.domain.post.domain.Post;
import com.example.meogobackend.domain.post.domain.facade.PostFacade;
import com.example.meogobackend.domain.user.domain.User;
import com.example.meogobackend.domain.user.domain.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateHeartService {
    private final HeartRepository heartRepository;
    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final HeartFacade heartFacade;

    @Transactional
    public HeartResponse createHeart(Long postId) {
        User user = userFacade.getCurrentUser();
        Post post = postFacade.getPost(postId);

        if (heartFacade.hasUserGivenHeartToPost(user, post)) {
            throw HeartExistException.EXCEPTION;
        }

        heartRepository.save(
                Heart.builder()
                        .user(user)
                        .post(post)
                        .build());

        post.addHeartCount();
        return new HeartResponse(post.getHeartCount());
    }
}