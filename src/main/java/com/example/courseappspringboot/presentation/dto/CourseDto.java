package com.example.courseappspringboot.presentation.dto;

import com.example.courseappspringboot.domain.model.course.Category;
import com.example.courseappspringboot.domain.model.course.CourseLevels;
import com.example.courseappspringboot.domain.model.user.User;

import java.sql.Timestamp;

public record CourseDto(int course_id,
                        User tutor,
                        String course_title,
                        String course_description,
                        int course_duration_weeks,
                        Timestamp created_at,
                        Double course_price,
                        Category course_category,
                        CourseLevels course_level) {
}
