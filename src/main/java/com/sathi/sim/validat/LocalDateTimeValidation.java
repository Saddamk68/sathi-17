package com.sathi.sim.validat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.sathi.sim.util.ValidLocalDateTimeFormat;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalDateTimeValidation implements ConstraintValidator<ValidLocalDateTimeFormat, String> {
	
    private String pattern;
    
    @Override
    public void initialize(ValidLocalDateTimeFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }
    
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isBlank()) {
            return true; // Skip validation for null or empty values, can be handled separately
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime.parse(value, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
	}


}
