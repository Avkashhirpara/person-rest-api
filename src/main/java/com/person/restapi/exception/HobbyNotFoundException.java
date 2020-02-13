package com.person.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for handling and propagating exceptions for
 * operations in Hobby class
 * @author Avkash
 * @version v1
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class HobbyNotFoundException extends RuntimeException {

    public HobbyNotFoundException(String message) {
        super(message);
    }
}
