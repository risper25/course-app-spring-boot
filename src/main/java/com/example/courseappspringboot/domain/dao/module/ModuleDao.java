package com.example.courseappspringboot.domain.dao.module;

import com.example.courseappspringboot.domain.model.course.Module;

import java.util.List;

public interface ModuleDao {
    Module addModule(Module module);
    void updateModule(Module module);
    Module findModuleById(int id);
    Module findModuleByTitle(String name);
    List<Module> findModulesByCourseId(int id);
    boolean deleteModulesByCourseId(int course_id);
    boolean deleteModuleById(int id);

}
