package com.example.meogobackend.domain.user.service;

import com.example.meogobackend.domain.user.domain.User;
import com.example.meogobackend.domain.user.domain.repository.UserRepository;
import com.example.meogobackend.domain.user.presentation.dto.response.UserInfoResponse;
import com.example.meogobackend.domain.user.service.exception.UserIdAlreadyExistException;
import com.example.meogobackend.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserUtil userUtil;
    private final UserRepository userRepository;

    public UserInfoResponse getUser() {
        User user = userUtil.getUser();

        return UserInfoResponse.builder()
                .nickname(user.getNickname())
                .accountId(user.getAccountId())
                .build();
    }
}