package com.example.courseappspringboot.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse<T> {
        private boolean Success;
        private String Message;
        private T data;
    }

