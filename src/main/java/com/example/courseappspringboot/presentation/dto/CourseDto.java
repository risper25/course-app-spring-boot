package com.example.courseappspringboot.presentation.dto;

import com.example.courseappspringboot.domain.model.course.Category;
import com.example.courseappspringboot.domain.model.course.CourseLevels;
import com.example.courseappspringboot.domain.model.user.User;

import java.sql.Timestamp;

public record CourseDto(
                        int tutor_id,
                        String course_title,
                        String course_description,
                        int course_duration_weeks,

                        Double course_price,
                        String category_name,
                        CourseLevels course_level) {
}
//tutor_id,"course_title","course_description","course_duration_weeks","course_price","category_name","course_level"