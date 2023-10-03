package com.example.courseappspringboot.domain.model.enrollment;

import com.example.courseappspringboot.domain.model.course.Course;
import com.example.courseappspringboot.domain.model.user.User;

import java.util.Date;

public class Enrollment {
   // private int enrolment_id;
    private User student;
    private Course course;
    private Date start_date;
    private Date end_date;
    private Boolean graduated;

}
