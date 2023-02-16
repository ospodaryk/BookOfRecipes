package com.project.recipes.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NullEntityReferenceException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(NullEntityReferenceException.class);

    public NullEntityReferenceException(String message) {
        super(message);
        logger.error("Thrown NullEntityReferenceException with msg:" + message);

    }
}
