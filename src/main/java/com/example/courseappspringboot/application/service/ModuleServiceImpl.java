package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.model.course.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService{
    @Override
    public Module addModule(Module module) {
        return null;
    }

    @Override
    public void updateModule(Module module) {

    }

    @Override
    public Module findModuleById(int id) {
        return null;
    }

    @Override
    public Module findModuleByTitle(String name) {
        return null;
    }

    @Override
    public List<Module> findModulesByCourseId(int id) {
        return null;
    }

    @Override
    public void deleteModulesByCourseId(int course_id) {

    }

    @Override
    public void deleteModuleById(int id) {

    }
}
