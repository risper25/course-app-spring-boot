package com.example.courseappspringboot.domain.model.course;

import com.example.courseappspringboot.domain.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Course implements Serializable {
    private int course_id;
    private String course_title;
    private String course_description;
    private int course_duration_weeks;
    private Timestamp created_at;
    private Double course_price;
    private Category course_category;
    private CourseLevels course_level;
    private User tutor;

    private List<Module> modules;


}
