package com.example.courseappspringboot.domain.model.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Module {
    private int module_id;
    private int course_id;
    private String module_title;
    private String module_description;
    private int module_order;
    private Date created_at;

}
