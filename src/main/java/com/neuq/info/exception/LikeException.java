package com.neuq.info.exception;

/**
 * Created by lihang on 2017/4/21.
 */
public class LikeException extends RuntimeException {
    public LikeException(String message) {
        super(message);
    }
    public LikeException(String message, Throwable cause) {
        super(message, cause);
    }
}
