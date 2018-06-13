package com.tom.core.exception;

public class GlobalException extends RuntimeException {

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(Throwable e) {
        super(e);
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }


}
