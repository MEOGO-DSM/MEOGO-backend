package com.example.meogobackend.domain.post.service.exception;

import com.example.meogobackend.global.error.exception.BaseException;
import com.example.meogobackend.global.error.exception.ErrorCode;

public class UserNotMatchException extends BaseException {
    public static final BaseException EXCEPTION = new UserNotMatchException();

    public UserNotMatchException(){
        super(ErrorCode.USER_NOT_MATCH);
    }
}
