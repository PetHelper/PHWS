package ru.pethelper.exception;

public class UsersPetException extends Exception {
    public UsersPetException() {
        super("No such pet for this user");
    }
}
