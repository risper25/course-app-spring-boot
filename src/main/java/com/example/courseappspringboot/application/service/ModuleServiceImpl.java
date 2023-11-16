package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.dao.module.ModuleDaoImpl;
import com.example.courseappspringboot.domain.model.course.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService{
    private final ModuleDaoImpl moduleDao;
    @Override
    public Module addModule(Module module) {
        return moduleDao.addModule(module);
    }

    @Override
    public void updateModule(Module module) {
        moduleDao.updateModule(module);

    }

    @Override
    public Module findModuleById(int id) {
        return moduleDao.findModuleById(id);
    }

    @Override
    public Module findModuleByTitle(String title) {
        return moduleDao.findModuleByTitle(title);
    }

    @Override
    public List<Module> findModulesByCourseId(int id) {
        return moduleDao.findModulesByCourseId(id);
    }

    @Override
    public boolean deleteModulesByCourseId(int course_id) {
          return moduleDao.deleteModulesByCourseId(course_id);
    }

    @Override
    public boolean deleteModuleById(int id) {
        return moduleDao.deleteModuleById(id);

    }
}
