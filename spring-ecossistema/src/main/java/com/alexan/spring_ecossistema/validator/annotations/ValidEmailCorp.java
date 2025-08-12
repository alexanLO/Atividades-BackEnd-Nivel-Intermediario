package com.alexan.spring_ecossistema.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.alexan.spring_ecossistema.validator.EmailValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmailCorp {

  String message() default "Email inválido para cadastro corporativo.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
