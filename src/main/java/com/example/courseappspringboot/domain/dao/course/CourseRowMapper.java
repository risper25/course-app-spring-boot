package com.example.courseappspringboot.domain.dao.course;

import com.example.courseappspringboot.domain.dao.category.CategoryDaoImpl;
import com.example.courseappspringboot.domain.dao.user.UserDaoImpl;
import com.example.courseappspringboot.domain.model.course.Category;
import com.example.courseappspringboot.domain.model.course.Course;
import com.example.courseappspringboot.domain.model.course.CourseLevels;
import com.example.courseappspringboot.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
@RequiredArgsConstructor
public class CourseRowMapper implements RowMapper<Course> {
    private CategoryDaoImpl categoryDao;
    private UserDaoImpl userDao;
    Logger logger= LoggerFactory.getLogger(CourseRowMapper.class);
    @Nullable
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course.CourseBuilder courseBuilder = Course.builder()
                .course_id(rs.getInt("course_id"))
                .tutor(User.builder().user_id(rs.getInt("tutor_id")).build())
                .course_title(rs.getString("course_title"))
                .course_duration_weeks(rs.getInt("course_duration_weeks"))
                .course_price(rs.getDouble("course_price"))
                .created_at(rs.getTimestamp("created_at"));
        return courseBuilder.build();
    }
}
