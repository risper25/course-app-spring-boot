package com.example.courseappspringboot.domain.dao.course;

import com.example.courseappspringboot.domain.model.course.Course;

import java.util.List;

public class CourseDaoImpl implements CourseDao{
    @Override
    public Course addCourse(Course course) {
        return null;
    }

    @Override
    public void updateCourse(Course course) {

    }

    @Override
    public void findCourseById(int id) {

    }

    @Override
    public List<Course> findCoursesByTutorId(int id) {
        return null;
    }

    @Override
    public void findCourseByTitle(String title) {

    }

    @Override
    public List<Course> findCoursesByLevel(String level) {
        return null;
    }

    @Override
    public void deleteCourseById(int id) {

    }

    @Override
    public void deleteCourseByTitle(String title) {

    }
}
