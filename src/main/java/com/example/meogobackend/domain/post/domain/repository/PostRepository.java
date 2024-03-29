package com.example.meogobackend.domain.post.domain.repository;

import com.example.meogobackend.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Page<Post> findAllByContentContainingOrderByIdDesc(String content, Pageable pageable);
}
