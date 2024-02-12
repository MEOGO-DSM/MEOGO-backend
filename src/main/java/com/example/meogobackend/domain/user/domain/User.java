package com.example.meogobackend.domain.user.domain;

import com.example.meogobackend.domain.user.domain.type.UserRole;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(nullable = false, length = 15, unique = true)
    private String accountId;

    @Column(nullable = false, length = 4)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Builder
    public User (String accountId, String nickname, String password, UserRole role){
        this.accountId = accountId;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }
}
