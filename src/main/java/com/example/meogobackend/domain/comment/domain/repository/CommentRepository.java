package com.example.meogobackend.domain.comment.domain.repository;

import com.example.meogobackend.domain.comment.domain.Comment;
import com.example.meogobackend.domain.post.domain.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findAllByPostOrderByIdDesc(Post post);
}
