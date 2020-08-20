package com.child.validator.verifyAccount;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerifyCodeValidator.class)
@Documented
public @interface ValidVerifyCode {

    String message() default "Invalid code.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
