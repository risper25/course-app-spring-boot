package com.example.courseappspringboot.presentation.mappers;

import com.example.courseappspringboot.domain.model.course.Content;
import com.example.courseappspringboot.presentation.dto.ContentDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContentMapper {
    ContentMapper INSTANCE= Mappers.getMapper(ContentMapper.class);

    Content DtoToDomain(ContentDto contentDto);

}
