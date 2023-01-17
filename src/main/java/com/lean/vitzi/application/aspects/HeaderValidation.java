package com.lean.vitzi.application.aspects;

import com.lean.vitzi.application.aspects.validators.KHeaderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
@Documented
@Constraint(validatedBy = KHeaderValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HeaderValidation {

    String message() default "{ConditionEnumValidator.defaultMessage}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}