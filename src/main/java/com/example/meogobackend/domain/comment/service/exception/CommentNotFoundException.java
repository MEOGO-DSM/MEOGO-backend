package com.example.meogobackend.domain.comment.service.exception;

import com.example.meogobackend.global.error.exception.BaseException;
import com.example.meogobackend.global.error.exception.ErrorCode;

public class CommentNotFoundException extends BaseException {
    public static final BaseException EXCEPTION = new CommentNotFoundException();

    public CommentNotFoundException(){
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}
