package com.child.validator.verifyAccount;

import com.child.dao.verifyAccount.VerifyAccountDao;
import com.child.model.VerifyAccount;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VerifyCodeValidator implements ConstraintValidator<ValidVerifyCode, String> {

    @Autowired
    private VerifyAccountDao verifyAccountDao;

    @Override
    public boolean isValid(String token, ConstraintValidatorContext context) {
        if(token.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Code not be empty")
                    .addConstraintViolation();
            return false;
        } else if(!verifyAccountDao.findByToken(token).isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Code verification not found")
                    .addConstraintViolation();
            return false;
        } else {
            VerifyAccount verifyAccount = verifyAccountDao.findByToken(token).get();
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
