package ru.pethelper.servlet.validation;

import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@javax.validation.Constraint(validatedBy = {PhoneNumberConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "Invalid PhoneNumber";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
