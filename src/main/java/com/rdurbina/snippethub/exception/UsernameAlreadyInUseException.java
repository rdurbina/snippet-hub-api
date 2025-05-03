package com.rdurbina.snippethub.exception;

public class UsernameAlreadyInUseException extends RuntimeException {
    public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
