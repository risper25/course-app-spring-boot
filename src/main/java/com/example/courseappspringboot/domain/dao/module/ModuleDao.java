package com.example.courseappspringboot.domain.dao.module;

import com.example.courseappspringboot.domain.model.course.Category;

import java.util.List;

public interface ModuleDao {
    Module addModule(Module module);
    void updateModule(Module module);
    void findModuleById(int id);
    void findModuleByTitle(String name);
    List<Module> findModulesByCourseId(int id);
    void deleteModuleById(int id);

}
