package com.example.meogobackend.domain.comment.service;

import com.example.meogobackend.domain.comment.domain.Comment;
import com.example.meogobackend.domain.comment.domain.repository.CommentRepository;
import com.example.meogobackend.domain.comment.presentation.dto.request.CommentRequest;
import com.example.meogobackend.domain.comment.service.exception.CommentNotFoundException;
import com.example.meogobackend.domain.post.domain.Post;
import com.example.meogobackend.domain.post.domain.repository.PostRepository;
import com.example.meogobackend.domain.post.service.exception.PostNotFoundException;
import com.example.meogobackend.domain.post.service.exception.UserNotMatchException;
import com.example.meogobackend.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Transactional
    public Long creat(CommentRequest request) {
        Post post = postRepository.findById(request.getId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        Comment comment = Comment.builder()
                .post(post)
                .user(userUtil.getUser())
                .content(request.getContent())
                .build();

        post.addCommentCount();
        commentRepository.save(comment);

        return comment.getId();
    }

    @Transactional
    public Long update(Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        if(!comment.getUser().getAccountId().equals(userUtil.getAccountId())) throw UserNotMatchException.EXCEPTION;

        return comment.update(request.getContent());
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);

        commentRepository.delete(comment);
        comment.getPost().minusCommentCount(); // commentCount 감소
    }
}
