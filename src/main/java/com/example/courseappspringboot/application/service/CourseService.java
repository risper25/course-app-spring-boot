package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.model.course.Course;

import java.util.List;

public interface CourseService {
    Course addCourse(Course course);
    void updateCourse(Course course);
    Course findCourseById(int id);
    List<Course> findAllCourses();
    List<Course> findCoursesByTutorId(int id);
    Course findCourseByTitle(String title);
    List<Course> findCoursesByLevel(String level);
    void deleteCourseById(int id);
    void deleteCourseByTitle(String title);

}
