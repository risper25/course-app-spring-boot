package com.example.courseappspringboot.domain.dao.course;

import com.example.courseappspringboot.domain.model.course.Category;
import com.example.courseappspringboot.domain.model.course.Course;

import java.util.List;

public interface CourseDao {
    Course addCourse(Course course);
    void updateCourse(Course course);
    void findCourseById(int id);
    List<Course> findCoursesByTutorId(int id);
    void findCourseByTitle(String title);
    List<Course> findCoursesByLevel(String level);
    void deleteCourseById(int id);
    void deleteCourseByTitle(String title);

}
