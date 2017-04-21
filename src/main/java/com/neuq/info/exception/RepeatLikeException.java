package com.neuq.info.exception;

/**
 * Created by lihang on 2017/4/21.
 */
public class RepeatLikeException extends LikeException {
    public RepeatLikeException(String message) {
        super(message);
    }

    public RepeatLikeException(String message, Throwable cause) {
        super(message, cause);
    }
}
