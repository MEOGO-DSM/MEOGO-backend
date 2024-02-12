package com.example.meogobackend.domain.user.presentation;

import antlr.Token;
import com.example.meogobackend.domain.user.presentation.dto.request.AdminSignupRequest;
import com.example.meogobackend.domain.user.presentation.dto.request.UserLoginRequest;
import com.example.meogobackend.domain.user.presentation.dto.request.UserSignupRequest;
import com.example.meogobackend.domain.user.presentation.dto.response.TokenResponse;
import com.example.meogobackend.domain.user.service.UserLoginService;
import com.example.meogobackend.domain.user.service.UserSignupService;
import com.example.meogobackend.global.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Transactional
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
//    private final UserUtil userUtil;
    private final UserSignupService userSignupService;
    private final UserLoginService userLoginService;

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signup(@RequestBody @Valid UserSignupRequest request) {
        return userSignupService.signup(request);
    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(@RequestBody @Valid UserLoginRequest request) {
        return userLoginService.login(request);
    }

    @PostMapping(value = "/manager/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse adminLogin(@RequestBody @Valid UserLoginRequest request) {
        return userLoginService.adminLogin(request);
    }

    @PostMapping(value = "/manager/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse adminSignup(@RequestBody @Valid AdminSignupRequest request) {
        return userSignupService.adminSignup(request);
    }
}
