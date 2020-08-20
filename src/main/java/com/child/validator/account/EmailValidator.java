package com.child.validator.account;

import com.child.service.account.AccountService;
import com.child.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Autowired
    private AccountService accountService;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email not be empty")
                    .addConstraintViolation();
            return false;
        }else if(!RegexUtils.validateEmail(email)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email invalid")
                    .addConstraintViolation();
            return false;
        }else if(accountService.findByEmail(email).isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email already Exists")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
