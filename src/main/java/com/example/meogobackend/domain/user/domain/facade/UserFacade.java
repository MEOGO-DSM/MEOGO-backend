package com.example.meogobackend.domain.user.domain.facade;

import com.example.meogobackend.domain.user.domain.User;
import com.example.meogobackend.domain.user.domain.repository.UserRepository;
import com.example.meogobackend.domain.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByUserId(accountId);
    }

    public User getUserByUserId(String accountId) {
        return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
