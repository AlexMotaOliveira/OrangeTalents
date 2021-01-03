package com.orangestalents.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestResponseError {

    private String error;

    public static RestResponseError fromValidationError(Errors errors) {
        RestResponseError resp = new RestResponseError();
        StringBuilder sb = new StringBuilder();

        for (ObjectError error : errors.getAllErrors()) {
            sb.append("-->" + error.getDefaultMessage()).append("; ");
        }

        resp.error = sb.toString();
        return resp;
    }

    public static RestResponseError fromMessageDuplicate(String message) {
        RestResponseError resp = new RestResponseError();
        resp.error = message;
        return resp;
    }

    public static RestResponseError userNotFoundException(String message) {
        RestResponseError resp = new RestResponseError();
        resp.error = message;
        return resp;
    }
}