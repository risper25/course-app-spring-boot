package com.example.courseappspringboot.presentation.mappers;

import com.example.courseappspringboot.domain.model.course.Module;
import com.example.courseappspringboot.presentation.dto.ModuleDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModuleMapper {
    ModuleMapper INSTANCE= Mappers.getMapper(ModuleMapper.class);
    Module DtoToDomain(ModuleDto moduleDto);
}
