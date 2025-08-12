package com.alexan.spring_ecossistema.validator;

import com.alexan.spring_ecossistema.validator.annotations.ValidEmailCorp;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmailCorp, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("O campo email não pode ser vazio.").addConstraintViolation();
            return false;
        }
        if (!value.contains("@Empresa")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("O email deve ser empresarial, exemplo: exemplo@Empresa.com")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
