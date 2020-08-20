package com.child.validator.account;

import com.child.utils.RegexUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Map<String, String> maps = new HashMap<String, String>();
        if (!RegexUtils.validatePassword(password, maps)) {
            String errorMessage = maps.get("errorMessage");
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();

            return false;
        }
        return true;
    }
}