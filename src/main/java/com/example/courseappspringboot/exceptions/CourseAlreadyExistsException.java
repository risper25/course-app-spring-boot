package com.example.courseappspringboot.exceptions;

public class CourseAlreadyExistsException extends RuntimeException{
    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}
