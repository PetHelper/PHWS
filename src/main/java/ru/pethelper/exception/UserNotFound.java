package ru.pethelper.exception;

public class UserNotFound extends Exception {
    public UserNotFound(final String email) {
        super("User with this email = " + email + " doesnt exists");
    }
}
