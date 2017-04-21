package com.neuq.info.exception;

/**
 * Created by lihang on 2017/4/21.
 */
public class RepeatUnLikeException extends  LikeException {
    public RepeatUnLikeException(String message) {
        super(message);
    }

    public RepeatUnLikeException(String message, Throwable cause) {
        super(message, cause);
    }
}
