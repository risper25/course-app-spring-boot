package com.example.courseappspringboot.presentation.controller;

import com.example.courseappspringboot.application.service.CategoryService;
import com.example.courseappspringboot.domain.model.course.Category;
import com.example.courseappspringboot.presentation.dto.ApiResponse;
import com.example.courseappspringboot.presentation.dto.CategoryDto;
import com.example.courseappspringboot.presentation.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    //add
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Category>> addCategory(@RequestBody CategoryDto request){
       Category category= CategoryMapper.INSTANCE.categoryDtoToDomain(request);
        Category data=categoryService.addCategory(category);
        ApiResponse<Category> response= ApiResponse
                .<Category>builder()
                .data(data)
                .isSuccess(true)
                .build();
         return ResponseEntity.ok(response);

    }
    //update
    @PutMapping("/update")
    public  ResponseEntity<ApiResponse<Category>> updateCategory(@RequestBody Category request){
        categoryService.updateCategory(request);
        ApiResponse<Category> response= ApiResponse
                .<Category>builder()
                .data(request)
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);



    }
    //find
    @GetMapping("/findAllCategories")
    public ResponseEntity<ApiResponse<Category>> findAllCategories(){
        List<Category> data=categoryService.findAllCategories();
        ApiResponse<Category> response= ApiResponse
                .<Category>builder()
                .dataList(data)
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);

    }
    @GetMapping("/findCategoryById/{id}")
    public ResponseEntity<ApiResponse<Category>> findCategoryById(@PathVariable int id){
        Category data=categoryService.findCategoryById(id);
        ApiResponse<Category> response= ApiResponse
                .<Category>builder()
                .data(data)
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/findCategoryByName/{name}")
    public ResponseEntity<ApiResponse<Category>> findCategoryByName(@PathVariable String name){
        Category data=categoryService.findCategoryByName(name);
        ApiResponse<Category> response= ApiResponse
                .<Category>builder()
                .data(data)
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);

    }
    //delete
    @DeleteMapping("deleteCategoryById/{id}")
    public ResponseEntity<ApiResponse<Category>> deleteCategoryById(@PathVariable int id){
        categoryService.deleteCategoryById(id);
        ApiResponse<Category> response= ApiResponse
                .<Category>builder()
                .isSuccess(true)
                .build();
        return ResponseEntity.ok(response);

    }


}
