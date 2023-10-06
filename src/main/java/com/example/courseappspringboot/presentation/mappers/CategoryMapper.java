package com.example.courseappspringboot.presentation.mappers;

import com.example.courseappspringboot.domain.model.course.Category;
import com.example.courseappspringboot.presentation.dto.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);
    @Mapping(target ="category_name", source ="category_name")
    Category categoryDtoToDomain(CategoryDto categoryDto);
}
