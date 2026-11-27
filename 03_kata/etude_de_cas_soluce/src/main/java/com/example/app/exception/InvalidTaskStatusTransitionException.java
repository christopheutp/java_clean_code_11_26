package com.example.app.exception;

public class InvalidTaskStatusTransitionException extends RuntimeException {
    public InvalidTaskStatusTransitionException(String format) {
        super(format);
    }
}
