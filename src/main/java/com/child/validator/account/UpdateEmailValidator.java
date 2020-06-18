package com.child.validator.account;

import com.child.dto.AccountUpdateDto;
import com.child.model.Account;
import com.child.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdateEmailValidator implements ConstraintValidator<ValidUpdateEmail, Object> {

    @Autowired
    private AccountService accountService;

    @Override
    public void initialize(ValidUpdateEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if(obj instanceof AccountUpdateDto) {
            AccountUpdateDto dto = (AccountUpdateDto) obj;
            Long id = dto.getId();
            String email = dto.getEmail();
            if(accountService.findById(id).isPresent()) {
                Account account = accountService.findById(id).get();
                if(!account.getEmail().equals(email)) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Email already taken")
                            .addPropertyNode("email").addConstraintViolation();
                    return false;
                }
            }
        }
        return true;
    }
}

