package com.example.courseappspringboot.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
