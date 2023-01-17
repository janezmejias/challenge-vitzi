package com.lean.vitzi.application.aspects.validators;

import com.lean.vitzi.application.aspects.HeaderValidation;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

import static com.lean.vitzi.application.api.ConstantsVitzi.REQUEST_TYPE;
import static com.lean.vitzi.application.api.ConstantsVitzi.REQUEST_TYPE_ERROR_MESSAGE;

/**
 * @author janez@vitzi.com
 * @version 2022-10-06
 */
public class KHeaderValidator implements ConstraintValidator<HeaderValidation, Map<String, String>> {

    @Override
    public boolean isValid(Map<String, String> headers, ConstraintValidatorContext context) {
        String requestType = headers.get(REQUEST_TYPE);
        if (Strings.isBlank(requestType)) {
            customMessageForValidation(context, REQUEST_TYPE_ERROR_MESSAGE);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void customMessageForValidation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
