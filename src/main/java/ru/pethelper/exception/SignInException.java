package ru.pethelper.exception;

public class SignInException extends Exception {
    public SignInException(final String email) {
        super("Password for this user = " + email + " doesnt match");
    }
}
