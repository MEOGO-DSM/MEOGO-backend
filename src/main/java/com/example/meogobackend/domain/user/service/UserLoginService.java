package com.example.meogobackend.domain.user.service;

import com.example.meogobackend.domain.user.domain.RefreshToken;
import com.example.meogobackend.domain.user.domain.User;
import com.example.meogobackend.domain.user.domain.repository.RefreshTokenRepository;
import com.example.meogobackend.domain.user.domain.repository.UserRepository;
import com.example.meogobackend.domain.user.domain.type.UserRole;
import com.example.meogobackend.domain.user.presentation.dto.request.UserLoginRequest;
import com.example.meogobackend.domain.user.presentation.dto.response.TokenResponse;
import com.example.meogobackend.domain.user.service.exception.NoPermissionException;
import com.example.meogobackend.domain.user.service.exception.PasswordMismatchException;
import com.example.meogobackend.domain.user.service.exception.UserNotFoundException;
import com.example.meogobackend.global.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse login(UserLoginRequest request) {

        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getAccountId()))
                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                        .accountId(user.getAccountId())
                        .refreshToken(jwtTokenProvider.generateRefreshToken(user.getAccountId()))
                        .build()).getRefreshToken())
                .build();
    }

    public TokenResponse adminLogin(UserLoginRequest request){
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(()->UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw PasswordMismatchException.EXCEPTION;
        }

        if(user.getRole()!= UserRole.ADMIN){
            throw NoPermissionException.EXCEPTION;
        }

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(user.getAccountId()))
                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                        .accountId(user.getAccountId())
                        .refreshToken(jwtTokenProvider.generateRefreshToken(user.getAccountId()))
                        .build()).getRefreshToken())
                .build();
    }
}
