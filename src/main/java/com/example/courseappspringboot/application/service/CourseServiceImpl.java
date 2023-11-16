package com.example.courseappspringboot.application.service;

import com.example.courseappspringboot.domain.dao.category.CategoryDaoImpl;
import com.example.courseappspringboot.domain.dao.course.CourseDaoImpl;
import com.example.courseappspringboot.domain.dao.user.UserDaoImpl;
import com.example.courseappspringboot.domain.model.course.Category;
import com.example.courseappspringboot.domain.model.course.Course;
import com.example.courseappspringboot.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    private final CourseDaoImpl dao;
    private final UserDaoImpl  uDao;
    private final CategoryDaoImpl categoryDao;

    @Override
    public boolean addCourse(Course course) {

        User tutor=uDao.getUserById(course.getTutor().getUser_id());
        Category category=categoryDao.findCategoryByName(course.getCourse_category().getCategory_name());
        course.setTutor(tutor);
        course.setCourse_category(category);
        return dao.addCourse(course);
    }

    @Override
    public boolean updateCourse(Course course) {

       /* User tutor=userDao.getUserById(course.getTutor().getUser_id());
        Category category=categoryDao.findCategoryById(course.getCourse_category().getCategory_id());
        course.setTutor(tutor);
        course.setCourse_category(category);*/
        return  dao.updateCourse(course);

    }

    @Override
    public Course findCourseById(int id) {
        Course course=dao.findCourseById(id);


        return course;
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
    public boolean deleteCourseById(int id) {
        return dao.deleteCourseById(id);

    }

    @Override
    public boolean deleteCourseByTutorId(int tutor_id) {
        return dao.deleteCourseByTutorId(tutor_id);
    }


}
