package com.example.meogobackend.domain.post.domain;

import com.example.meogobackend.domain.comment.domain.Comment;
import com.example.meogobackend.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false)
    private String createDate;

    @NotNull
    private Integer heartCount;

    @NotNull
    private Integer commentCount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(User user, String content, Integer heartCount, Integer commentCount){
        this.user = user;
        this.content = content;
        this.heartCount = 0;
        this.commentCount = 0;
        this.createDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy. MM. dd"));
    }

    public void addHeartCount() {
        this.heartCount += 1;
    }

    public void minusHeartCount() {
        this.heartCount -= 1;
    }

    public void addCommentCount() {
        this.commentCount += 1;
    }

    public void minusCommentCount() {
        this.commentCount -= 1;
    }

    public Long update(String content) {
        this.content = content;
        return this.id;
    }
}
