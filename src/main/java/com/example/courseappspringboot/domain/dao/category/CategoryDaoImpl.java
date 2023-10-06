package com.example.courseappspringboot.domain.dao.category;

import com.example.courseappspringboot.domain.model.course.Category;
import com.example.courseappspringboot.exceptions.CategoryAlreadyExistsException;
import com.example.courseappspringboot.exceptions.CategoryNotFoundException;
import com.example.courseappspringboot.exceptions.CustomDatabaseException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao{
    private final JdbcTemplate jdbcTemplate;
    Logger logger= LoggerFactory.getLogger(CategoryDaoImpl.class);
    @Override
    public Category addCategory(Category category) {
        try {
            String sql = "INSERT INTO course_categories(category_name) VALUES(?)";
            jdbcTemplate.update(sql, category.getCategory_name());
            return category;
        }
        catch (DuplicateKeyException e){
            logger.error("category id or name already exists:");
            throw new CategoryAlreadyExistsException("category with id or name already exists: " + e.getMessage());

        }
        catch (DataAccessException e){
            logger.error("Failure to add category to  database:");
            throw new CustomDatabaseException("Failure to add category to  database:" + e.getMessage());


        }
    }

    @Override
    public void updateCategory(Category category) {
        try{
        String sql="UPDATE course_categories SET category_name=?";
        jdbcTemplate.update(sql,category.getCategory_name());

        }
        catch (DataAccessException e){
            logger.error("Failure to update category to  database:");
            throw new CustomDatabaseException("Failure to update category to  database:" + e.getMessage());

        }

    }

    @SneakyThrows
    @Override
    public List<Category> findAllCategories(){
        try {
            String sql="Select * FROM course_categories";
            return jdbcTemplate.query(sql,new CategoryRowMapper());

        }
        catch (DataAccessException e){
            logger.error("Error getting course categories");
            throw new Exception("Error getting course categories");

        }
    }

    @Override
    public Category findCategoryById(int id) {
        try{
        String sql="SELECT * FROM course_categories WHERE category_id=?";
        Object[] args ={id};
        Category category=jdbcTemplate.queryForObject(sql,new CategoryRowMapper(),args);
        if(category==null){
            throw new CategoryNotFoundException("Category with id"+id+"does not exist");
        }
        return category;
        }
        catch (DataAccessException e){

            logger.error("Failed to find category category with id "+id);
            throw new CustomDatabaseException("Failure to find category category with id "+id+": " + e.getMessage());


        }


    }

    @Override
    public Category findCategoryByName(String name) {
        try{
        String sql="SELECT * FROM course_categories WHERE category_name=?";
        Object[] args ={name};
        Category category=jdbcTemplate.queryForObject(sql,new CategoryRowMapper(),args);
            if(category==null){
                throw new CategoryNotFoundException("Category with name"+name+"does not exist");
            }
            return category;
        }

        catch (DataAccessException e){

            logger.error("Failed to find category category with name "+name);
            throw new CustomDatabaseException("Failure to find category category with name "+name+": " + e.getMessage());


        }


    }

    @Override
    public void deleteCategoryById(int id) {
        try{
        String sql="DELETE FROM course_categories WHERE category_id=?";
        Object[] args ={id};
        jdbcTemplate.update(sql,args);
        }
        catch (DataAccessException e){

            logger.error("Failure to delete category with id "+ id );
            throw new CustomDatabaseException("Failure to delete category with id "+ id +": " + e.getMessage());


        }


    }
 //Todo :sql injection prevention
    @Override
    public void deleteCategoryByName(String name) {
        try {
            String sql = "DELETE FROM course_categories WHERE category_name=?";
            Object[] args = {name};
            jdbcTemplate.update(sql, args);
        }
        catch (DataAccessException e){

            logger.error("Failure to delete category with name "+ name);
            throw new CustomDatabaseException("Failure to delete category with name "+ name +": "+ e.getMessage());


        }

    }
}
