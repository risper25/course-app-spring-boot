package com.example.courseappspringboot.exceptions;

public class InvalidCourseLevelException extends RuntimeException{
    public InvalidCourseLevelException(String message){
        super(message);
    }
}
