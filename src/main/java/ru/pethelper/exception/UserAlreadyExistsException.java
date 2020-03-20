package ru.pethelper.exception;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(final String invalidCredentials) {
        super("User with this credentials = " + invalidCredentials + " already exists");
    }

    public UserAlreadyExistsException(final long phoneNumber) {
        super("User with this phone number = " + phoneNumber + " already exists");
    }
}
