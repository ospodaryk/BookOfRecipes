package com.project.recipes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class FieldsValidationErrors {
    private static final Logger logger = LoggerFactory.getLogger(FieldsValidationErrors.class);

    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";\n");
        }
        logger.info("Method return errors to Client combined it.");
        throw new EntitiyNotCreatedException(errorMsg.toString());
    }
}
