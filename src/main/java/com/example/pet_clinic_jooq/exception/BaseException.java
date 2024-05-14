package com.example.pet_clinic_jooq.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    public abstract HttpStatus status();

    public BaseException(String message) {
        super(message, null, false, false);
    }
}
