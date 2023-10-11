package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.model.course.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService{
    @Override
    public Content addContent(Content content) {
        return null;
    }

    @Override
    public void updateContent(Content content) {

    }

    @Override
    public void findContentById(int id) {

    }

    @Override
    public List<Content> findContentsByModuleId(int id) {
        return null;
    }

    @Override
    public void deleteModuleById(int id) {

    }
}
