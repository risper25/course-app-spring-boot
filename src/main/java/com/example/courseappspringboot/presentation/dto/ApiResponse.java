package com.example.courseappspringboot.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse<T> {
        private boolean isSuccess;
        private String Message;
        private T data;
        private List<T> dataList;

    }

