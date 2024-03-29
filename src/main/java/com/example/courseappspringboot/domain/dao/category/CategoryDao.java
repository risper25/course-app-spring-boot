package com.example.courseappspringboot.domain.dao.category;

import com.example.courseappspringboot.domain.model.course.Category;

import java.util.List;

public interface CategoryDao {
    Category addCategory(Category category);
    void updateCategory(Category category);
    List<Category> findAllCategories();
    Category findCategoryById(int id);
    Category findCategoryByName(String name);
    void deleteCategoryById(int id);
    void deleteCategoryByName(String name);


}
