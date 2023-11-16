package com.example.courseappspringboot.presentation.mappers;

import com.example.courseappspringboot.domain.model.course.Course;
import com.example.courseappspringboot.presentation.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {
    CourseMapper INSTANCE= Mappers.getMapper(CourseMapper.class);
    @Mapping(source = "tutor_id", target = "tutor.user_id")
    @Mapping(source = "category_name", target = "course_category.category_name")
    Course courseDtoToDomain(CourseDto courseDto);
}
