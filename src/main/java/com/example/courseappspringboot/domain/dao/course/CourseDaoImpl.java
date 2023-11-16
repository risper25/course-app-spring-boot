package com.example.courseappspringboot.domain.dao.course;

import com.example.courseappspringboot.domain.dao.module.ModuleDaoImpl;
import com.example.courseappspringboot.domain.model.course.Course;
import com.example.courseappspringboot.domain.model.course.CourseLevels;
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
    @Override //todo : start on delete  dao,modify module and content
    public boolean addCourse(Course course) {
        try {
            String sql = "INSERT INTO courses(tutor_id,category_id,course_title,course_description,course_duration_weeks,course_level,course_price) VALUES (?,?,?,?,?,CAST(? AS course_levels),?)";
            int rows_affected=jdbcTemplate.update(sql,
                    course.getTutor().getUser_id(),
                    course.getCourse_category().getCategory_id(),
                    course.getCourse_title(),
                    course.getCourse_description(),
                    course.getCourse_duration_weeks(),
                    course.getCourse_level().toString(),
                    course.getCourse_price()
            );

            return rows_affected>0;
        }
        catch (DuplicateKeyException e){
            logger.error("course with course title "+course.getCourse_title()+" already exists:"+ e.getMessage());
            throw new CourseAlreadyExistsException("course with title"+course.getCourse_title()+" already exists: " + e.getMessage());

        }
        catch (DataAccessException e){
            logger.error("Failure to add course to  database:"+ e.getMessage());
            throw new CustomDatabaseException("Failure to add course to  database");


        }
    }

    @Override
    public boolean updateCourse(Course course) {
        try{
            String sql="UPDATE courses SET tutor_id=?,category_id=?,course_description=?,course_duration_weeks=?,course_level=CAST(? AS course_levels),course_price=? WHERE course_title=?";
            int rows_affected=jdbcTemplate.update(sql,course.getTutor().getUser_id()
                                    ,course.getCourse_category().getCategory_id()
                                    ,course.getCourse_description()
                                    ,course.getCourse_duration_weeks()
                                    ,course.getCourse_level().toString()
                                    ,course.getCourse_price()
                    ,course.getCourse_title());
            return rows_affected>0;

        }
        catch (DataAccessException e){
            logger.error("Failure to update course:"+ e);
            throw new CustomDatabaseException("Failure to update course:" + e.getMessage());
        }

    }

    @Override
    public List<Course> findAllCourses() {

        try{
            String sql="SELECT * FROM courses AS c " +
                    "LEFT JOIN users_ AS u ON c.tutor_id = u.user_id " +
                    "LEFT JOIN modules AS m ON c.course_id = m.course_id " +
                    "LEFT JOIN contents AS ct ON m.module_id = ct.module_id";

            return jdbcTemplate.query(sql, new CourseResultSetExtractor());
        }
        catch (DataAccessException e){
            logger.error("Failure to load courses:" +e);
            throw new CustomDatabaseException("Failure to load courses:" + e.getMessage());

        }
    }
    @Override
    public Course findCourseById(int id) {
        try{
            String sql="SELECT * FROM courses AS c " +
                    "LEFT JOIN users_ AS u ON c.tutor_id = u.user_id " +
                    "LEFT JOIN modules AS m ON c.course_id = m.course_id " +
                    "LEFT JOIN contents AS ct ON m.module_id = ct.module_id " +
                    "WHERE c.course_id=?";

            Object[] args ={id};
            List<Course> courses=jdbcTemplate.query(sql, new CourseResultSetExtractor(),args);
            if(courses.isEmpty()){
                throw new CourseNotFoundException("Course with id"+id+"does not exist");
            }
            return courses.get(0);

        }
        catch(DataAccessException e){
            throw new CourseNotFoundException("Course with id: "+id+" not found");
        }

    }



    @Override
    public List<Course> findCoursesByTutorId(int tutor_id) {
        try{
            String sql="SELECT * FROM courses AS c " +
                    "LEFT JOIN users_ AS u ON c.tutor_id = u.user_id " +
                    "LEFT JOIN modules AS m ON c.course_id = m.course_id " +
                    "LEFT JOIN contents AS ct ON m.module_id = ct.module_id " +
                    "WHERE c.tutor_id=?";
            Object[] args={tutor_id};
            return jdbcTemplate.query(sql, new CourseResultSetExtractor(),args);
        }
        catch(DataAccessException e){
            throw new CourseNotFoundException("Course with  tutor_id: "+ tutor_id+" not found");
        }


    }

    @Override
    public Course findCourseByTitle(String title) {

        try{
            String sql="SELECT * FROM courses AS c " +
                    "LEFT JOIN users_ AS u ON c.tutor_id = u.user_id " +
                    "LEFT JOIN modules AS m ON c.course_id = m.course_id " +
                    "LEFT JOIN contents AS ct ON m.module_id = ct.module_id " +
                    "WHERE c.course_title=?";
            Object[] args={title};
            List<Course> courses=jdbcTemplate.query(sql, new CourseResultSetExtractor(),args);
            if(courses.isEmpty()){
                throw new CourseNotFoundException("Course with title"+title+"does not exist");
            }
            return courses.get(0);
        }
        catch(DataAccessException e){
            throw new CourseNotFoundException("Course with  tutor_id: "+ title+" not found");
        }


    }

    @Override
    public List<Course> findCoursesByLevel(String level) {
        if(!isValidLevel(level)){
            throw new InvalidCourseLevelException("Invalid course level: " + level);
        }

       try{
            String sql="SELECT * FROM courses AS c " +
                    "LEFT JOIN users_ AS u ON c.tutor_id = u.user_id " +
                    "LEFT JOIN modules AS m ON c.course_id = m.course_id " +
                    "LEFT JOIN contents AS ct ON m.module_id = ct.module_id " +
                    "WHERE c.course_level=CAST(? AS course_levels)";
            Object[] args={level};
            List<Course> courses=jdbcTemplate.query(sql, new CourseResultSetExtractor(),args);

            return courses;
        }
        catch(DataAccessException e){
            throw new CourseNotFoundException("database error");
        }
    }
    private boolean isValidLevel(String level){
        for (CourseLevels courseLevel : CourseLevels.values()) {
            if (courseLevel.name().equals(level)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteCourseById(int id) {
        try{
            String sql= "DELETE FROM courses as c " +
                   "USING modules AS m, contents AS ct " +
                   "WHERE c.course_id = m.course_id " +
                   "AND m.module_id = ct.module_id " +
                   "AND c.course_id = ?";
            Object[]args ={id};
            int rows_affected=jdbcTemplate.update(sql,args);
            return rows_affected>0;

        }
        catch(DataAccessException e){
            throw new CustomDatabaseException("Failed to delete Course with id: "+id);
        }


    }

    @Override
    public boolean deleteCourseByTutorId(int tutor_id) {
        try{
            String sql= "DELETE FROM courses as c " +
                    "USING modules AS m, contents AS ct " +
                    "WHERE c.course_id = m.course_id " +
                    "AND m.module_id = ct.module_id " +
                    "AND c.tutor_id = ?";
            Object[]args ={tutor_id};
            int rows_affected=jdbcTemplate.update(sql,args);
            return rows_affected>0;

        }
        catch(DataAccessException e){
            throw new CustomDatabaseException("Failed to delete Course with id: "+tutor_id);
        }

    }
}
