package com.example.courseappspringboot.domain.model.course;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Category {
    private int category_id;
    private String category_name;
}
