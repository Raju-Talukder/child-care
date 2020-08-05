package com.child.validator.account;

import com.child.utils.RegexUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringValidator implements ConstraintValidator<ValidString, String> {
    @Override
    public void initialize(ValidString constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String data, ConstraintValidatorContext context) {
        if (data.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Required")
                    .addConstraintViolation();
            return false;
        }else if(!RegexUtils.commonValidation(data)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Only character is allowed")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
