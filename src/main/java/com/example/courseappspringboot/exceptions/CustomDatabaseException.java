package com.example.courseappspringboot.exceptions;

public class CustomDatabaseException extends RuntimeException{
    public CustomDatabaseException(String message){
        super(message);
    }
   ;

}
