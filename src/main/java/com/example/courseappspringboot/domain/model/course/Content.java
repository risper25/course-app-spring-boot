package com.example.courseappspringboot.domain.model.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Content {

    private int content_id ;
    private int module_id ;
    private int content_order ;
    private String video_url ;
    private String content_text;
    private Date created_at;

}
