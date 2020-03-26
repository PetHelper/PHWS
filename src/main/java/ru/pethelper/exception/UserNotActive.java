package ru.pethelper.exception;

public class UserNotActive extends Exception {
    public UserNotActive(final String email) {
        super("User with this email = " + email + " must activate account on email");
    }
}
