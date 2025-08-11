package com.alexan.spring_ecossistema.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.alexan.spring_ecossistema.validator.constraints.EmailValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmailCorp {

      String message() default "O email deve ser empresarial, exemplo: exemplo@Empresa.com";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
