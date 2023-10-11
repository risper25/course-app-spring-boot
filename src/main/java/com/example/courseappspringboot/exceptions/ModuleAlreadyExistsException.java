package com.example.courseappspringboot.exceptions;

public class ModuleAlreadyExistsException extends RuntimeException {
    public ModuleAlreadyExistsException(String message) {
        super(message);
    }
}
