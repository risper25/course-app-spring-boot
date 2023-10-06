package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.dao.category.CategoryDaoImpl;
import com.example.courseappspringboot.domain.model.course.Category;
import com.example.courseappspringboot.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDaoImpl repository;


    @Override
    public Category addCategory(Category category) {
        if(category==null){
            throw new ValidationException("Category name cannot be null");
        }

        return repository.addCategory(category);
    }

    @Override
    public void updateCategory(Category category) {

        repository.updateCategory(category);

    }

    @Override
    public List<Category> findAllCategories() {
        return repository.findAllCategories();
    }

    @Override
    public Category findCategoryById(int id) {

        return repository.findCategoryById(id);
    }

    @Override
    public Category findCategoryByName(String name) {
        return repository.findCategoryByName(name);
    }

    @Override
    public void deleteCategoryById(int id) {
        repository.deleteCategoryById(id);

    }

    @Override
    public void deleteCategoryByName(String name) {
        repository.findCategoryByName(name);

    }
}
