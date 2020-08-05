package com.child.validator.account;

import com.child.utils.RegexUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberValidator implements ConstraintValidator<ValidNumber, String> {
    @Override
    public void initialize(ValidNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String data, ConstraintValidatorContext context) {
        if (data.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Required")
                    .addConstraintViolation();
            return false;
        }else if(!RegexUtils.numberValidation(data)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Only number is allowed")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
