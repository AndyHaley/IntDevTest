/*
 * ********************************************************************
 * Simple Inventory API
 * 
 * Copyright (C) 2019 QUT
 * Developed by: Andrew Haley
 * 
 * ********************************************************************
 */
package au.edu.qut.SimpleInventoryApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}