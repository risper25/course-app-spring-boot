package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.model.course.Module;

import java.util.List;

public interface ModuleService {
    Module addModule(Module module);
    void updateModule(Module module);
    Module findModuleById(int id);
    Module findModuleByTitle(String title);
    List<Module> findModulesByCourseId(int id);
    boolean deleteModulesByCourseId(int course_id);
    boolean deleteModuleById(int id);
}
