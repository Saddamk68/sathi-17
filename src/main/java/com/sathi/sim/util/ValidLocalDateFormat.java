package com.sathi.sim.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sathi.sim.validat.LocalDateValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LocalDateValidation.class)
public @interface ValidLocalDateFormat {

    String message() default "Invalid date format. Expected format: yyyy-MM-dd";
    
    String pattern() default "yyyy-MM-dd";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
}
