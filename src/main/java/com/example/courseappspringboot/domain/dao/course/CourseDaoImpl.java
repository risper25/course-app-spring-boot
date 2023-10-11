package com.example.courseappspringboot.domain.dao.course;

import com.example.courseappspringboot.domain.dao.module.ModuleDaoImpl;
import com.example.courseappspringboot.domain.model.course.Course;
import com.example.courseappspringboot.domain.model.course.Module;
import com.example.courseappspringboot.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class CourseDaoImpl implements CourseDao{
    private final JdbcTemplate jdbcTemplate;
    private final ModuleDaoImpl moduleDao;
    Logger logger= LoggerFactory.getLogger(CourseDaoImpl.class);
    @Override
    public Course addCourse(Course course) {
        try {
            String sql = "INSERT INTO courses(tutor_id ,category_id,course_title,course_description,course_duration_weeks,course_level,course_price) VALUES(?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, course.getTutor().getUser_id()
                                    ,course.getCourse_category().getCategory_id()
                                    ,course.getCourse_title()
                                    ,course.getCourse_description()
                                   ,course.getCourse_duration_weeks()
                                   ,course.getCourse_level()
                                   ,course.getCourse_price()
            );
            return course;
        }
        catch (DuplicateKeyException e){
            logger.error("course with course title "+course.getCourse_title()+" already exists:");
            throw new CourseAlreadyExistsException("course with title"+course.getCourse_title()+" already exists: " + e.getMessage());

        }
        catch (DataAccessException e){
            logger.error("Failure to add course to  database:");
            throw new CustomDatabaseException("Failure to add course to  database:" + e.getMessage());


        }
    }

    @Override
    public void updateCourse(Course course) {
        try{
            String sql="UPDATE courses SET tutor_id=?,category_id=?,course_title=?,course_description=?,course_duration_weeks=?,course_level=?,course_price=?";
            jdbcTemplate.update(sql,course.getTutor().getUser_id()
                                    ,course.getCourse_category().getCategory_id()
                                    ,course.getCourse_title()
                                    ,course.getCourse_description()
                                    ,course.getCourse_duration_weeks()
                                    ,course.getCourse_level()
                                    ,course.getCourse_price());

        }
        catch (DataAccessException e){
            logger.error("Failure to update course:");
            throw new CustomDatabaseException("Failure to update course:" + e.getMessage());
        }

    }
    @Override
    public List<Course> findAllCourses() {
        try{
            String sql="SELECT * FROM courses " +
                    "INNER JOIN modules ON courses.course_id = modules.course_id\n" +
                    "INNER JOIN content ON modules.module_id = content.module_id";

            return jdbcTemplate.query(sql,new CourseRowMapper());
        }
        catch (DataAccessException e){
            logger.error("Failure to load courses:");
            throw new CustomDatabaseException("Failure to load courses:" + e.getMessage());

        }
    }
    @Override
    public Course findCourseById(int id) {
        try{
            String sql="SELECT * FROM courses WHERE course_id=?";
            Object[] args ={id};
            Course course= jdbcTemplate.queryForObject(sql,new CourseRowMapper(),args);
            if(course==null){
                throw new CourseNotFoundException("Category with id"+id+"does not exist");
            }
            else{
                //also load courses
                List<Module> modules=moduleDao.findModulesByCourseId(id);
                course.setModules(modules);
                return course;
            }

        }
        catch(DataAccessException e){
            throw new CourseNotFoundException("Course with id: "+id+" not found");
        }

    }



    @Override
    public List<Course> findCoursesByTutorId(int tutor_id) {
        try{
            String sql="SELECT * FROM courses WHERE tutor_id=?";
            Object[] args ={tutor_id};

            return jdbcTemplate.query(sql,new CourseRowMapper(),args);
        }
        catch(DataAccessException e){
            throw new CourseNotFoundException("Course with  tutor_id: "+ tutor_id+" not found");
        }


    }

    @Override
    public Course findCourseByTitle(String title) {

        try{
            String sql="SELECT * FROM courses WHERE course_title=?";
            Object[] args ={title};
            return jdbcTemplate.queryForObject(sql,new CourseRowMapper(),args);
        }
        catch(DataAccessException e){
            throw new CourseNotFoundException("Course with  tutor_id: "+ title+" not found");
        }


    }

    @Override
    public List<Course> findCoursesByLevel(String level) {
        try{
            String sql="SELECT * FROM courses WHERE course_level=?";
            Object[] args ={level};
            return jdbcTemplate.query(sql,new CourseRowMapper(),args);
        }
        catch(DataAccessException e){
            throw new CourseNotFoundException("Course with  level: "+ level+" not found");
        }
    }

    @Override
    public void deleteCourseById(int id) {
        try{
            String sql="DELETE * FROM courses WHERE course_id=?";
            Object[]args ={id};
            jdbcTemplate.update(sql,args);
            moduleDao.deleteModulesByCourseId(id);
        }
        catch(DataAccessException e){
            throw new CustomDatabaseException("Failed to delete Course with id: "+id);
        }

    }

    @Override
    public void deleteCourseByTitle(String title) {

    }
}
