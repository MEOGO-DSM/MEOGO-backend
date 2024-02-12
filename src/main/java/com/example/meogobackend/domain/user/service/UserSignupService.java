package com.example.meogobackend.domain.user.service;

import com.example.meogobackend.domain.user.domain.RefreshToken;
import com.example.meogobackend.domain.user.domain.User;
import com.example.meogobackend.domain.user.domain.repository.RefreshTokenRepository;
import com.example.meogobackend.domain.user.domain.repository.UserRepository;
import com.example.meogobackend.domain.user.domain.type.UserRole;
import com.example.meogobackend.domain.user.presentation.dto.request.AdminSignupRequest;
import com.example.meogobackend.domain.user.presentation.dto.request.UserSignupRequest;
import com.example.meogobackend.domain.user.presentation.dto.response.TokenResponse;
import com.example.meogobackend.domain.user.service.exception.UserIdAlreadyExistException;
import com.example.meogobackend.global.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignupService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenResponse signup(UserSignupRequest request){
        if (userRepository.existsByAccountId(request.getAccountId())) {
            throw new UserIdAlreadyExistException();
        }

        User user = userRepository.save(User.builder()
                .role(UserRole.USER)
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .build());

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getAccountId()))
                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                        .accountId(user.getAccountId())
                        .refreshToken(jwtTokenProvider.generateRefreshToken(user.getAccountId()))
                        .build()).getRefreshToken())
                .build();
    }

    @Transactional
    public TokenResponse adminSignup(AdminSignupRequest request){
        User user = userRepository.save(User.builder()
                .role(UserRole.ADMIN)
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .build());

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getAccountId()))
                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                        .accountId(user.getAccountId())
                        .refreshToken(jwtTokenProvider.generateRefreshToken(user.getAccountId()))
                        .build()).getRefreshToken())
                .build();
    }
}
