package com.example.courseappspringboot.domain.model.course;

import java.util.Date;
import java.util.List;

public class Module {
    private int module_id;
    private int course_id;
    private Date created_at;


    private List<Content> contents;
}
