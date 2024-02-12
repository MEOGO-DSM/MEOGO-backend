package com.example.meogobackend.domain.heart.domain.facade;

import com.example.meogobackend.domain.heart.domain.repository.HeartRepository;
import com.example.meogobackend.domain.post.domain.Post;
import com.example.meogobackend.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HeartFacade {
    private final HeartRepository heartRepository;

    public boolean hasUserGivenHeartToPost(User user, Post post) {
        return heartRepository.existsByUserAndPost(user, post);
    }
}