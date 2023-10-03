package com.example.courseappspringboot.domain.model.course;

import com.example.courseappspringboot.domain.model.user.User;


import java.util.Date;
import java.util.List;

public class Course {
    private int course_id;
    private User tutor;
    private String course_title;
    private String course_description;
    private Date course_duration;
    private Date created_at;
    private Double course_price;
    private Category course_category;
    private CourseLevels course_level;


}
