package com.talents.orange.demo.infrastructure;

public class DuplicateUserException extends  Exception {

    public DuplicateUserException(String message) {
        super(message);
    }
}
