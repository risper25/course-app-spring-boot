package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.model.course.Module;

import java.util.List;

public interface ModuleService {
    Module addModule(Module module);
    void updateModule(Module module);
    Module findModuleById(int id);
    Module findModuleByTitle(String name);
    List<Module> findModulesByCourseId(int id);
    void deleteModulesByCourseId(int course_id);
    void deleteModuleById(int id);
}
