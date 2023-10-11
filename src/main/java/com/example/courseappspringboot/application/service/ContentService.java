package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.model.course.Content;

import java.util.List;

public interface ContentService {
    Content addContent(Content content);
    void updateContent(Content content);
    void findContentById(int id);
    List<Content> findContentsByModuleId(int id);
    void deleteModuleById(int id);
}
