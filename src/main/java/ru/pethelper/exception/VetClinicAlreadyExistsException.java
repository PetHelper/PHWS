package ru.pethelper.exception;

public class VetClinicAlreadyExistsException extends Exception {
    public VetClinicAlreadyExistsException(final String name) {
        super("Vetclinic with this name = " + name + " already exists");
    }
}
