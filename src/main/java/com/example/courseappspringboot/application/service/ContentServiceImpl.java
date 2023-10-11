package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.dao.content.ContentDaoImpl;
import com.example.courseappspringboot.domain.model.course.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService{
    private final ContentDaoImpl contentDao;
    @Override
    public Content addContent(Content content) {
        return contentDao.addContent(content);
    }

    @Override
    public void updateContent(Content content) {
        contentDao.updateContent(content);

    }

    @Override
    public Content findContentById(int id) {
       return contentDao.findContentById(id);

    }

    @Override
    public List<Content> findContentsByModuleId(int id) {
        return contentDao.findContentsByModuleId(id);
    }

    @Override
    public void deleteContentById(int id) {
        contentDao.deleteContentById(id);

    }

    @Override
    public void deleteContentByModuleId(int module_id) {
        contentDao.deleteContentByModuleId(module_id);
    }

}
