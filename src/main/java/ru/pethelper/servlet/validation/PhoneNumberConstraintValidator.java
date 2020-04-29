package ru.pethelper.servlet.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberConstraintValidator implements ConstraintValidator<ValidPhoneNumber, Long> {
    @Override
    public void initialize(final ValidPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Long value, final ConstraintValidatorContext context) {
        if (value == 0) {
            return false;
        }
        return true;
    }
}
