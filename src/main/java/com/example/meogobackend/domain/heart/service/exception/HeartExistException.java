package com.example.meogobackend.domain.heart.service.exception;

import com.example.meogobackend.global.error.exception.BaseException;
import com.example.meogobackend.global.error.exception.ErrorCode;

public class HeartExistException extends BaseException {
    public static final BaseException EXCEPTION = new HeartExistException();

    private HeartExistException() {
        super(ErrorCode.HEART_EXIST);
    }
}
