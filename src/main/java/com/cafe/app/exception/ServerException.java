package com.cafe.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class ServerException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -4088614155093800555L;

    public ServerException() {
        super("Something went wrong. Please try again.");
    }
}