package com.cafe.app.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) throws Exception{
        throw new ServerException();
    }
}