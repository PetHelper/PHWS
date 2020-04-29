package ru.pethelper.servlet.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberConstraintValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    @Override
    public void initialize(final ValidPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String phone, final ConstraintValidatorContext context) {
        try {
            Phonenumber.PhoneNumber phoneNumberProto = phoneUtil.parse(phone, "RU");
            return phoneUtil.isValidNumber(phoneNumberProto);
        } catch (NumberParseException e) {
            return false;
        }
    }
}
