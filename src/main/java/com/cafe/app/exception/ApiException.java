package com.cafe.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApiException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -8320984790375032991L;

    public ApiException(String error) {
        super(error);
    }
}