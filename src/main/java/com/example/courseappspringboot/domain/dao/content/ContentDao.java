package com.example.courseappspringboot.domain.dao.content;

import com.example.courseappspringboot.domain.model.course.Content;

import java.util.List;

public interface ContentDao{
    Module addContent(Content content);
    void updateContent(Content content);
    void findContentById(int id);
    List<Content> findContentsByModuleId(int id);
    void deleteModuleById(int id);
}
