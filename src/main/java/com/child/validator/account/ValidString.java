package com.child.validator.account;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringValidator.class)
@Documented
public @interface ValidString {
    String message() default "Invalid input.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
