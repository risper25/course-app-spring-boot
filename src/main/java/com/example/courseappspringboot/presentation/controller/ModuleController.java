package com.example.courseappspringboot.presentation.controller;

import com.example.courseappspringboot.application.service.ModuleServiceImpl;
import com.example.courseappspringboot.domain.model.course.Module;
import com.example.courseappspringboot.presentation.dto.ApiResponse;
import com.example.courseappspringboot.presentation.dto.ModuleDto;
import com.example.courseappspringboot.presentation.mappers.ModuleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/module")
@RequiredArgsConstructor
public class ModuleController {
    private final ModuleServiceImpl service;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Module>> addModule(@RequestBody ModuleDto request){
        Module data= ModuleMapper.INSTANCE.DtoToDomain(request);
        Module module=service.addModule(data);
        ApiResponse<Module> response= ApiResponse.<Module>builder()
                .isSuccess(true)
                .Message("content added successfully")
                .data(module)
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Module>> updateModule(@RequestBody ModuleDto request){
        Module data= ModuleMapper.INSTANCE.DtoToDomain(request);
        service.updateModule(data);
        ApiResponse<Module> response= ApiResponse.<Module>builder()
                .isSuccess(true)
                .Message("content added successfully")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ApiResponse<Module>> findModuleById(@PathVariable int id){
        Module module=service.findModuleById(id);
        ApiResponse<Module> response= ApiResponse.<Module>builder()
                .isSuccess(true)
                .Message("module found successfully")
                .data(module)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<ApiResponse<Module>>  findModuleByTitle(@PathVariable String title){
        Module module=service.findModuleByTitle(title);
        ApiResponse<Module> response= ApiResponse.<Module>builder()
                .isSuccess(true)
                .Message("module found successfully")
                .data(module)
                .build();
        return ResponseEntity.ok(response);

    }
    @GetMapping("/findByCourseId/{course_id}")
    public ResponseEntity<ApiResponse<Module>> findModulesByCourseId(@PathVariable int course_id){
        List<Module> modules=service.findModulesByCourseId(course_id);
        ApiResponse<Module> response= ApiResponse.<Module>builder()
                .isSuccess(true)
                .Message("module found successfully")
                .dataList(modules)
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/deleteByCourseId/{course_id}")
    public ResponseEntity<ApiResponse<Module>> deleteModulesByCourseId(@PathVariable int course_id){
        boolean success=service.deleteModulesByCourseId(course_id);
        ApiResponse<Module> response= ApiResponse.<Module>builder()
                .isSuccess(success)
                .Message("Deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/deleteByModuleId/{module_id}")
    public ResponseEntity<ApiResponse<Module>> deleteModuleById(@PathVariable int module_id){
        boolean success=service.deleteModuleById(module_id);
        ApiResponse<Module> response= ApiResponse.<Module>builder()
                .isSuccess(success)
                .Message("Deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}
