package com.example.courseappspringboot.domain.dao.course;

import com.example.courseappspringboot.domain.dao.category.CategoryDaoImpl;
import com.example.courseappspringboot.domain.dao.user.UserDaoImpl;
import com.example.courseappspringboot.domain.model.course.Course;
import com.example.courseappspringboot.domain.model.course.CourseLevels;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
@RequiredArgsConstructor
public class CourseRowMapper implements RowMapper<Course> {
    CategoryDaoImpl categoryDao;
    UserDaoImpl userDao;
    @Nullable
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Course.builder().course_id(rs.getInt("course_id"))
                .tutor(userDao.getUserById(rs.getInt("tutor_id")))
                .course_category(categoryDao.findCategoryById(rs.getInt(" category_id")))
                .course_title(rs.getString("course_title"))
                .course_duration_weeks(rs.getInt("course_duration_weeks"))
                .course_level(CourseLevels.valueOf(rs.getString("course_level")))//Todo: handle if it is null
                .course_price(rs.getDouble("course_price"))
                .created_at(rs.getTimestamp("created_at"))
                .build();
    }
}
