package com.example.courseappspringboot.presentation;

import com.example.courseappspringboot.exceptions.*;
import com.example.courseappspringboot.presentation.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomDatabaseException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomDatabaseException(CustomDatabaseException ex){
        ApiResponse<Object> response= new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex){
        ApiResponse<Object> response= new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }

    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleCourseAlreadyExistsException(CourseAlreadyExistsException ex){
        ApiResponse<Object> response= new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ContentAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleContentAlreadyExistsException(ContentAlreadyExistsException ex){
        ApiResponse<Object> response= new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ModuleAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleModuleAlreadyExistsException(ModuleAlreadyExistsException ex){
        ApiResponse<Object> response= new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleCategoryNotFoundException(CategoryNotFoundException ex){
        ApiResponse<Object> response= new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleCourseNotFoundException(CourseNotFoundException ex){
        ApiResponse<Object> response= new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(ModuleNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleModuleNotFoundException(ModuleNotFoundException ex){
        ApiResponse<Object> response= new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleContentNotFoundException(ContentNotFoundException ex){
        ApiResponse<Object> response= new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex){
        ApiResponse<Object> response= new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }


}
