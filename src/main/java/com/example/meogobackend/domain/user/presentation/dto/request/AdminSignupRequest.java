package com.example.meogobackend.domain.user.presentation.dto.request;

import com.example.meogobackend.domain.user.domain.type.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminSignupRequest {
    @Size(min = 1, max = 4)
    private String nickname;

    @Size(min = 5, max = 15)
    private String accountId;

    @NotNull
    private String role;

    @Pattern(regexp = "^(?=.*[!@#$%^&*])(?=.{1,20}$).*", message = "비밀번호는 특수문자 한 개가 포함되어야합니다.")
    private String password;
}
