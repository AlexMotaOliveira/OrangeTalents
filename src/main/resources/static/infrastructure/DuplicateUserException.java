package com.orangestalents.orange.demo.infrastructure;

public class DuplicateUserException extends  Exception {

    public DuplicateUserException(String message) {
        super(message);
    }
}
