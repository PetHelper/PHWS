package ru.pethelper.exception;

public class UserNotActive extends Exception {
    public UserNotActive(final String email) {
        super(email);
    }
}
