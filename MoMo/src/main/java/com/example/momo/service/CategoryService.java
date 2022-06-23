package com.example.momo.service;

import com.example.momo.dto.category.CategoryDto;

import java.util.Collection;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto);
    Collection<CategoryDto> readAllCategory();
    CategoryDto readCategory(Long categoryId);
    boolean updateCategory(Long categoryId, CategoryDto dto);
    boolean deleteCategory(Long categoryId);

}
