package com.example.meogobackend.domain.heart.service.exception;

import com.example.meogobackend.global.error.exception.BaseException;
import com.example.meogobackend.global.error.exception.ErrorCode;

public class DeleteHeartExistException extends BaseException {
    public static final BaseException EXCEPTION= new DeleteHeartExistException();

    public DeleteHeartExistException() {
        super(ErrorCode.DELETE_HEART_EXIST);
    }
}
