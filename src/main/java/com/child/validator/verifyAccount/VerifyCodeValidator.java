package com.child.validator.verifyAccount;

import com.child.model.VerifyAccount;
import com.child.repository.VerifyAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerifyCodeValidator implements ConstraintValidator<ValidVerifyCode, String> {

    @Autowired
    private VerifyAccountRepository verifyAccountRepository;

    @Override
    public boolean isValid(String token, ConstraintValidatorContext context) {
        if(token.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Code not be empty")
                    .addConstraintViolation();
            return false;
        } else if(!verifyAccountRepository.findByToken(token).isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Code verification not found")
                    .addConstraintViolation();
            return false;
        } else {
            VerifyAccount verifyAccount = verifyAccountRepository.findByToken(token).get();
            if(verifyAccount.isExpired()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Verification code has expired")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
