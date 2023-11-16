package com.example.courseappspringboot.presentation.controller;

import com.example.courseappspringboot.application.service.CourseServiceImpl;
import com.example.courseappspringboot.domain.dao.course.CourseDaoImpl;
import com.example.courseappspringboot.domain.model.course.Course;
import com.example.courseappspringboot.presentation.dto.ApiResponse;
import com.example.courseappspringboot.presentation.dto.CourseDto;
import com.example.courseappspringboot.presentation.mappers.CategoryMapper;
import com.example.courseappspringboot.presentation.mappers.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseServiceImpl service;
    Logger logger= LoggerFactory.getLogger(CourseController.class);

   @PostMapping("/add")
    public ResponseEntity<ApiResponse<Course>> addCourse(@RequestBody CourseDto request){


       Course course= CourseMapper.INSTANCE.courseDtoToDomain(request);

       boolean success=service.addCourse(course);

     ApiResponse<Course> response= ApiResponse.<Course>builder()
             .isSuccess(success)
             .Message("course saved successfully")
             .build();
          logger.info(String.valueOf(response));
     return ResponseEntity.ok(response);
   }

   @PutMapping("/update")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@RequestBody CourseDto request){

       Course course= CourseMapper.INSTANCE.courseDtoToDomain(request);;
       boolean success=service.updateCourse(course);
       ApiResponse<Course> response= ApiResponse.<Course>builder()
               .isSuccess(success)
               .Message("course updated successfully")
               .build();

       return ResponseEntity.ok(response);


   }
   @GetMapping("/findById/{id}")
    public ResponseEntity<ApiResponse<Course>> findCourseById(@PathVariable int id){
       Course course=service.findCourseById(id);
       ApiResponse<Course> response= ApiResponse.<Course>builder()
               .isSuccess(true)
               .data(course)
               .Message("course loaded successfully")
               .build();

       return ResponseEntity.ok(response);


    }
    @GetMapping("/findAll")
    public ResponseEntity<ApiResponse<Course>> findAllCourses(){
        List<Course> courses=service.findAllCourses();
        ApiResponse<Course> response= ApiResponse.<Course>builder()
                .isSuccess(true)
                 .dataList(courses)
                .Message("courses loaded successfully")
                .build();

        return ResponseEntity.ok(response);


    }


    @GetMapping("/findCoursesByTutorId/{tutor_id}")
    public ResponseEntity<ApiResponse<Course>> findCoursesByTutorId(@PathVariable int tutor_id){
        List<Course> courses=service.findCoursesByTutorId(tutor_id);
        ApiResponse<Course> response= ApiResponse.<Course>builder()
                .isSuccess(true)
                .dataList(courses)
                .Message("courses loaded successfully")
                .build();

        return ResponseEntity.ok(response);


    }

    @GetMapping("/findCourseByTitle/{title}")
    public ResponseEntity<ApiResponse<Course>> findCoursesByTutorId(@PathVariable String title){
        Course course=service.findCourseByTitle(title);
        ApiResponse<Course> response= ApiResponse.<Course>builder()
                .isSuccess(true)
                .data(course)
                .Message("courses loaded successfully")
                .build();

        return ResponseEntity.ok(response);


    }

    @GetMapping("/findCoursesByLevel/{level}")
    public ResponseEntity<ApiResponse<Course>> findCoursesByLevel(@PathVariable String level){
        List<Course> courses=service.findCoursesByLevel(level);
        String message="courses loaded successfully";
        if(courses.isEmpty()){
            message="No courses";
        }
        ApiResponse<Course> response= ApiResponse.<Course>builder()
                .isSuccess(true)
                .dataList(courses)
                .Message(message)
                .build();

        return ResponseEntity.ok(response);


    }

   @DeleteMapping("/deleteCourseById/{id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourseById(@PathVariable int id){
       boolean success=service.deleteCourseById(id);
        ApiResponse<Course> response= ApiResponse.<Course>builder()
                .isSuccess(success)
                .Message("deleted successfully")
                .build();

        return ResponseEntity.ok(response);


    }

    @DeleteMapping("/deleteCourseByTutorId/{tutor_id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourseByTitle(@PathVariable int tutor_id){
        boolean success=service.deleteCourseByTutorId(tutor_id);
        ApiResponse<Course> response= ApiResponse.<Course>builder()
                .isSuccess(true)
                .Message("deleted successfully")
                .build();

        return ResponseEntity.ok(response);


    }
}
