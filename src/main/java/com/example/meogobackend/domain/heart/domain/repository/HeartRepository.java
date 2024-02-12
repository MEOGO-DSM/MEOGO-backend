package com.example.meogobackend.domain.heart.domain.repository;

import com.example.meogobackend.domain.heart.domain.Heart;
import com.example.meogobackend.domain.post.domain.Post;
import com.example.meogobackend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Boolean existsByUserAndPost(User user, Post post);

    void deleteByUserAndPost(User user, Post post);
}
