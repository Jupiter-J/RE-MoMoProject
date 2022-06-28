package com.example.momo.controller;

import com.example.momo.dto.category.CategoryDto;
import com.example.momo.dto.response.BaseErrorResponse;
import com.example.momo.dto.response.BaseResponse;
import com.example.momo.dto.response.ErrorCode;
import com.example.momo.exception.NotFoundException;
import com.example.momo.service.CategoryServiceJpa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryServiceJpa categoryServiceJpa;
    public CategoryController(CategoryServiceJpa categoryServiceJpa){
        this.categoryServiceJpa = categoryServiceJpa;
    }


    @PostMapping
    public ResponseEntity<BaseResponse<CategoryDto>> createCategory(@RequestBody CategoryDto dto){
        //파라미터로 받는다 -> valid 어노테이션 검색
        return ResponseEntity.ok(BaseResponse.success(categoryServiceJpa.createCategory(dto)));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<Collection<CategoryDto>>> readAllCategory(){
        return ResponseEntity.ok(BaseResponse.success(categoryServiceJpa.readAllCategory()));
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<BaseResponse<CategoryDto>> readCategory(@PathVariable("categoryId") Long categoryId){

        CategoryDto categoryDto = this.categoryServiceJpa.readCategory(categoryId);

        if (categoryDto == null)
            return ResponseEntity
                    .notFound()
                    .build();
        else
            return ResponseEntity.ok(BaseResponse.success(categoryDto));
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<BaseResponse<?>> updateCategory(@PathVariable("categoryId") Long categoryId,
                                                      @RequestBody CategoryDto dto){

        if (!categoryServiceJpa.updateCategory(categoryId, dto))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(BaseResponse.success(categoryId));
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<BaseResponse<?>> deleteCategory(@PathVariable("categoryId") Long categoryId){
        if (!categoryServiceJpa.deleteCategory(categoryId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(BaseResponse.success(categoryId));
    }






}
