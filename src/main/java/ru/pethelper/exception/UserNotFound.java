package ru.pethelper.exception;

public class UserNotFound extends Exception {
    public UserNotFound(final String email) {
        super(email);
    }
}
