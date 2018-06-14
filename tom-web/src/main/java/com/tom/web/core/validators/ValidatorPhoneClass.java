package com.tom.web.core.validators;

import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidatorPhoneClass implements ConstraintValidator<ValidatorPhone, Object> {
    private String values;

    private boolean canNull;

    @Override
    public void initialize(ValidatorPhone constraintAnnotation) {
        this.canNull = constraintAnnotation.canNull();
    }


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (value == null || Strings.isNullOrEmpty(value.toString())) {
            if (!canNull) isValid = false;
        } else if (!Pattern.matches("^[1]{1}[3,4,5,7,8,9]{1}\\d{9}$", value.toString())) {
            isValid = false;
        }
        return isValid;
    }
}
