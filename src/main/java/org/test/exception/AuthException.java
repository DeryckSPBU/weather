package org.test.exception;

public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }
}
