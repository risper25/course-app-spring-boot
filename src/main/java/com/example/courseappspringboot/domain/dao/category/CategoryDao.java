package com.example.courseappspringboot.domain.dao.category;

import com.example.courseappspringboot.domain.model.course.Category;

public interface CategoryDao {
    Category addCategory(Category category);
    void updateCategory(Category category);
    void findCategoryById(int id);
    void findCategoryByName(String name);
    void deleteCategoryById(int id);
    void deleteCategoryByName(String name);


}
