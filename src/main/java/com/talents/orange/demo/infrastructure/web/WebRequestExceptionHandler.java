package com.talents.orange.demo.infrastructure.web;

import com.talents.orange.demo.infrastructure.DuplicateUserException;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebRequestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestResponseError handleException(RepositoryConstraintViolationException e){
        return RestResponseError.fromValidationError(e.getErrors());
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestResponseError handleException(DuplicateUserException e){
        return RestResponseError.fromMessageDuplicate(e.getMessage());
    }
}
