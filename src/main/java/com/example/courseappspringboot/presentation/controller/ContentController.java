package com.example.courseappspringboot.presentation.controller;

import com.example.courseappspringboot.application.service.ContentServiceImpl;
import com.example.courseappspringboot.domain.model.course.Content;
import com.example.courseappspringboot.presentation.dto.ApiResponse;
import com.example.courseappspringboot.presentation.dto.ContentDto;
import com.example.courseappspringboot.presentation.mappers.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentServiceImpl service;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Content>> addContent(@RequestBody ContentDto request){
        Content content= ContentMapper.INSTANCE.DtoToDomain(request);
        Content data=service.addContent(content);
        ApiResponse<Content> response= ApiResponse.<Content>builder()
                .isSuccess(true)
                .Message("Added successfully")
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Content>> updateContent(@RequestBody ContentDto request){
        Content content= ContentMapper.INSTANCE.DtoToDomain(request);
        service.updateContent(content);
        ApiResponse<Content> response= ApiResponse.<Content>builder()
                .isSuccess(true)
                .Message("Updated successfully")
                .build();
        return ResponseEntity.ok(response);


    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ApiResponse<Content>> findContentById(@PathVariable int id){
        Content content=service.findContentById(id);
        ApiResponse<Content> response= ApiResponse.<Content>builder()
                .isSuccess(true)
                .Message("Found successfully")
                .data(content)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByModuleId/{module_id}")
    public ResponseEntity<ApiResponse<Content>> findContentsByModuleId(@PathVariable int module_id){
        List<Content> content=service.findContentsByModuleId(module_id);
        ApiResponse<Content> response= ApiResponse.<Content>builder()
                .isSuccess(true)
                .Message("Found successfully")
                .dataList(content)
                .build();

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ApiResponse<Content>> deleteContentById(@PathVariable int id){
        service.deleteContentById(id);
        ApiResponse<Content> response= ApiResponse.<Content>builder()
                .isSuccess(true)
                .Message("deleted successfully")
                .build();

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/deleteByModuleId/{module_id}")
    public ResponseEntity<ApiResponse<Content>> deleteContentByModuleId(@PathVariable int module_id){
        service.deleteContentByModuleId(module_id);
        ApiResponse<Content> response= ApiResponse.<Content>builder()
                .isSuccess(true)
                .Message("deleted successfully")
                .build();

        return ResponseEntity.ok(response);
    }

}
