package com.example.courseappspringboot.exceptions;

public class ContentAlreadyExistsException extends RuntimeException {
    public ContentAlreadyExistsException(String message) {
        super(message);
    }
}
