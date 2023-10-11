package com.example.courseappspringboot.domain.dao.content;

import com.example.courseappspringboot.domain.model.course.Content;

import java.util.List;

public interface ContentDao{
    Content addContent(Content content);
    void updateContent(Content content);
    Content findContentById(int id);
    List<Content> findContentsByModuleId(int id);
    void deleteContentById(int id);
    void deleteContentByModuleId(int module_id);

}
