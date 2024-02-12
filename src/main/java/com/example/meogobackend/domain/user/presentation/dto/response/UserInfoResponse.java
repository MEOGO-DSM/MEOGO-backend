package com.example.meogobackend.domain.user.presentation.dto.response;

import com.example.meogobackend.domain.user.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {
    private String nickname;
    private String accountId;
}
