package com.orangestalents.exception;

import com.orangestalents.exception.web.DuplicateUserException;
import com.orangestalents.exception.web.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRequestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestResponseError handleException(DuplicateUserException e) {
        return RestResponseError.fromMessageDuplicate(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestResponseError handleException(UserNotFoundException e) {
        return RestResponseError.userNotFoundException(e.getMessage());
    }
}
