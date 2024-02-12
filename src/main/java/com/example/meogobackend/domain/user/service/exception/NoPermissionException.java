package com.example.meogobackend.domain.user.service.exception;

import com.example.meogobackend.global.error.exception.BaseException;
import com.example.meogobackend.global.error.exception.ErrorCode;

public class NoPermissionException extends BaseException {
    public static final BaseException EXCEPTION = new NoPermissionException();

    public NoPermissionException(){
        super(ErrorCode.NO_PERMISSION);
    }
}
