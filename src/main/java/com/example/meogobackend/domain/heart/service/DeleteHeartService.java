package com.example.meogobackend.domain.heart.service;

import com.example.meogobackend.domain.heart.domain.facade.HeartFacade;
import com.example.meogobackend.domain.heart.domain.repository.HeartRepository;
import com.example.meogobackend.domain.heart.presentation.dto.HeartResponse;
import com.example.meogobackend.domain.heart.service.exception.DeleteHeartExistException;
import com.example.meogobackend.domain.post.domain.Post;
import com.example.meogobackend.domain.post.domain.facade.PostFacade;
import com.example.meogobackend.domain.user.domain.User;
import com.example.meogobackend.domain.user.domain.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteHeartService {
    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final HeartFacade heartFacade;
    private final HeartRepository heartRepository;

    @Transactional
    public HeartResponse deleteHeart(Long postId) {
        User user = userFacade.getCurrentUser();
        Post post = postFacade.getPost(postId);

        if (!heartFacade.hasUserGivenHeartToPost(user, post)) {
            throw DeleteHeartExistException.EXCEPTION;
        }

        heartRepository.deleteByUserAndPost(user, post);

        post.minusHeartCount();
        return new HeartResponse(post.getHeartCount());
    }
}