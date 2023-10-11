package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.dao.course.CourseDaoImpl;
import com.example.courseappspringboot.domain.model.course.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    CourseDaoImpl dao;

    @Override
    public Course addCourse(Course course) {
        return dao.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        dao.updateCourse(course);

    }

    @Override
    public Course findCourseById(int id) {
        return dao.findCourseById(id);
    }

    @Override
    public List<Course> findAllCourses() {
        return dao.findAllCourses();
    }

    @Override
    public List<Course> findCoursesByTutorId(int tutor_id) {
        return dao.findCoursesByTutorId(tutor_id);
    }

    @Override
    public Course findCourseByTitle(String title) {
        return dao.findCourseByTitle(title);
    }

    @Override
    public List<Course> findCoursesByLevel(String level) {
        return dao.findCoursesByLevel(level);
    }

    @Override
    public void deleteCourseById(int id) {
        dao.deleteCourseById(id);

    }

    @Override
    public void deleteCourseByTitle(String title) {
        deleteCourseByTitle(title);

    }
}
