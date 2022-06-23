package com.example.momo.controller;

import com.example.momo.dto.category.CategoryDto;
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
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto dto){
        return ResponseEntity.ok(categoryServiceJpa.createCategory(dto));
    }

    @GetMapping
    public ResponseEntity<Collection<CategoryDto>> readAllCategory(){
        return ResponseEntity.ok(categoryServiceJpa.readAllCategory());
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryDto> readCategory(@PathVariable("categoryId") Long categoryId){

        CategoryDto categoryDto = this.categoryServiceJpa.readCategory(categoryId);

        if (categoryDto == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(categoryDto);
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable("categoryId") Long categoryId,
                                                      @RequestBody CategoryDto dto){

        if (!categoryServiceJpa.updateCategory(categoryId, dto))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId){
        if (!categoryServiceJpa.deleteCategory(categoryId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }






}
