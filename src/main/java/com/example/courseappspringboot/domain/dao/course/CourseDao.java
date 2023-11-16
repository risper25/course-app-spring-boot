package com.example.courseappspringboot.domain.dao.course;

import com.example.courseappspringboot.domain.model.course.Course;

import java.util.List;

public interface CourseDao {
    boolean addCourse(Course course);
    boolean updateCourse(Course course);
    Course findCourseById(int id);
    List<Course> findAllCourses();
    List<Course> findCoursesByTutorId(int id);
    Course findCourseByTitle(String title);
    List<Course> findCoursesByLevel(String level);
    Boolean deleteCourseById(int id);
    boolean deleteCourseByTutorId(int tutor_id);

}
